package com.selftaughtdev.data.network.retrofit.interceptors

import com.selftaughtdev.data.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
            .build()
        return chain.proceed(request)
    }
}