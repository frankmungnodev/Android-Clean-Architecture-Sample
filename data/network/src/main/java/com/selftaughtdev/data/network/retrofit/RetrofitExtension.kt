package com.selftaughtdev.data.network.retrofit

import com.selftaughtdev.domain.exception.NetworkException
import retrofit2.Call
import retrofit2.Response

fun <T> Call<T>.executeOrThrow(): T {

    val response = this.execute()

    return response.getBodyOrThrowNetworkException()
}

fun <T> Response<T>.getBodyOrThrowNetworkException(): T {

    if (this.isSuccessful.not()) {
        val errorString = this.errorBody()
            ?.byteStream()
            ?.bufferedReader()
            ?.use { it.readText() }
        throw NetworkException(errorString, this.code())
    }

    return body() ?: throw NetworkException()
}