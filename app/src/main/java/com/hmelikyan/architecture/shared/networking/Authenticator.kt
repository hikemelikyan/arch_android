package com.hmelikyan.architecture.shared.networking

import okhttp3.Interceptor
import okhttp3.Response

class Authenticator : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
            .newBuilder()
            .header("Content-Type", "application/json")
            .build()

        val response = chain.proceed(newRequest)
        response.cacheResponse()
        return response
    }
}