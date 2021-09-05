package com.selftaughtdev.data.network.retrofit.factory

import com.selftaughtdev.domain.base.model.NetworkResponse
import com.selftaughtdev.domain.exception.AlertException
import com.selftaughtdev.domain.exception.SnackBarException
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.*
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.net.UnknownHostException

class NetworkResponseAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        // suspend functions wrap the response type in `Call`
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        // check first that the return type is `ParameterizedType`
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<NetworkResponse<<Foo>> or Call<NetworkResponse<out Foo>>"
        }

        // get the response type inside the `Call` type
        val responseType = getParameterUpperBound(0, returnType)
        // if the response type is not ApiResponse then we can't handle this type, so we return null
        if (getRawType(responseType) != NetworkResponse::class.java) {
            return null
        }

        // the response type is ApiResponse and should be parameterized
        check(responseType is ParameterizedType) { "Response must be parameterized as NetworkResponse<Foo> or NetworkResponse<out Foo>" }

        val successBodyType = getParameterUpperBound(0, responseType)
        val errorBodyType = getParameterUpperBound(0, responseType)

        val errorBodyConverter =
            retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)

        return NetworkResponseAdapter<Any, Any>(successBodyType, errorBodyConverter)
    }

    private class NetworkResponseAdapter<S : Any, E : Any>(
        private val successType: Type,
        private val errorBodyConverter: Converter<ResponseBody, E>
    ) : CallAdapter<S, Call<NetworkResponse<S, E>>> {

        override fun responseType(): Type = successType

        override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> {
            return NetworkResponseCall(call, errorBodyConverter)
        }
    }

    private class NetworkResponseCall<S : Any, E : Any>(
        private val delegate: Call<S>,
        private val errorConverter: Converter<ResponseBody, E>
    ) : Call<NetworkResponse<S, E>> {

        val caller = this@NetworkResponseCall

        override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {

            return delegate.enqueue(object : Callback<S> {

                override fun onResponse(call: Call<S>, response: Response<S>) {
                    if (response.isSuccessful) {
                        val networkResponse = getBodyOrError(response)
                        callback.onResponse(caller, Response.success(networkResponse))
                    } else {
                        val networkResponse = NetworkResponse.Error(
                            AlertException(
                                response.code(),
                                "Something went wrong",
                                "${response.code()}"
                            )
                        )
                        callback.onResponse(caller, Response.success(networkResponse))
                    }
                }

                override fun onFailure(call: Call<S>, throwable: Throwable) {
                    val networkResponse = when (throwable) {
                        is UnknownHostException -> NetworkResponse.Error(
                            SnackBarException(
                                -1,
                                "Check your internet connection and try again!"
                            )
                        )
                        is IOException -> NetworkResponse.Error(
                            SnackBarException(
                                -1,
                                "Check your internet connection and try again!"
                            )
                        )
                        is HttpException -> NetworkResponse.Error(
                            AlertException(
                                throwable.code(),
                                throwable.message(),
                                "Error code: ${throwable.code()}"
                            )
                        )
                        else -> NetworkResponse.Error(throwable)
                    }
                    callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
                }
            })
        }

        private fun getBodyOrError(response: Response<S>): NetworkResponse<S, E> {
            return when (val body = response.body()) {
                null -> NetworkResponse.Error(SnackBarException(response.code(), "No data found!"))
                else -> NetworkResponse.Success(body)
            }
        }

        override fun isExecuted() = delegate.isExecuted

        override fun clone() = NetworkResponseCall(delegate.clone(), errorConverter)

        override fun isCanceled() = delegate.isCanceled

        override fun cancel() = delegate.cancel()

        override fun execute(): Response<NetworkResponse<S, E>> {
            throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
        }

        override fun request(): Request = delegate.request()
        override fun timeout(): Timeout = delegate.timeout()
    }
}