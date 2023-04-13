package com.example.ktorandroid.data.remote.engine

import okhttp3.Interceptor
import okhttp3.Response

object OkhttpInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val aRequest = chain.request()
        val aResponse = chain.proceed(aRequest)
        when(aResponse.code) {
            200 -> {
                println("200 Good response")
            }
            400 -> {
                println("400 Bad request")
            }
            401 -> {
                println("401 Unauthorized request")
            }
            403 -> {
                println("403vForbidden request")
            }
            404 -> {
                println("404 Not found")
            }
            500 -> {
                println("500 Some shit network error")
            }
        }
        return  aResponse
    }

}