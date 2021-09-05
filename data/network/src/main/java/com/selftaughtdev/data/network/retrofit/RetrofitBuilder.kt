package com.selftaughtdev.data.network.retrofit

import com.selftaughtdev.data.network.BuildConfig
import com.selftaughtdev.data.network.HttpClient
import com.selftaughtdev.data.network.retrofit.factory.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitBuilder @Inject constructor() {

    private var connectionTimeout = HttpClient.CONNECT_TIMEOUT
    private var writeTimeout = HttpClient.WRITE_TIMEOUT
    private var readTimeout = HttpClient.READ_TIMEOUT
    private var okHttpClientBuilder: OkHttpClient.Builder? = null
    private var interceptors = mutableListOf<Interceptor>()
    private var logEnable: Boolean = BuildConfig.DEBUG
    private var isSupportAuthorization = false
    private var authenticator: Authenticator? = null
    private var baseUrl: String = BuildConfig.BASE_URL

//    @Inject
//    lateinit var oauthRefreshAuthenticator: OauthRefreshAuthenticator

    fun setTimeout(
        connectionTimeout: Long = HttpClient.CONNECT_TIMEOUT,
        writeTimeout: Long = HttpClient.WRITE_TIMEOUT,
        readTimeout: Long = HttpClient.READ_TIMEOUT
    ): RetrofitBuilder {
        this.connectionTimeout = connectionTimeout
        this.writeTimeout = writeTimeout
        this.readTimeout = readTimeout
        return this
    }

    fun setOkHttpClientBuilder(okHttpClientBuilder: OkHttpClient.Builder): RetrofitBuilder {
        this.okHttpClientBuilder = okHttpClientBuilder
        return this
    }

    fun addInterceptors(vararg interceptor: Interceptor): RetrofitBuilder {
        interceptors.addAll(interceptor)
        return this
    }

    fun loggingEnable(enable: Boolean): RetrofitBuilder {
        this.logEnable = enable
        return this
    }

    fun supportAuthorization(enable: Boolean): RetrofitBuilder {
        this.isSupportAuthorization = enable
        return this
    }

    fun setCustomAuthorization(authenticator: Authenticator): RetrofitBuilder {
        this.authenticator = authenticator
        return this
    }

    fun setBaseURL(baseUrl: String): RetrofitBuilder {
        this.baseUrl = baseUrl
        return this
    }

    fun build(): Retrofit {
        val clientBuilder = okHttpClientBuilder ?: OkHttpClient.Builder().apply {
            connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            writeTimeout(writeTimeout, TimeUnit.SECONDS)
            readTimeout(readTimeout, TimeUnit.SECONDS)

            if (logEnable) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }

            interceptors.forEach { addInterceptor(it) }
        }

        val moshi = Moshi.Builder().build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(clientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }
}