package com.example.ktorandroid.data.remote.engine

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import kotlinx.coroutines.withTimeout

private const val TIME_OUT = 60000

val OkHttpclient = HttpClient(OkHttp) {
    engine {
        config {
            followRedirects(true)
        }
        addInterceptor(OkhttpInterceptor)
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(Logging) {
        level = LogLevel.ALL
    }
}

fun ApiClient(): HttpClient {
    return HttpClient(OkHttp) {
        install(HttpTimeout, )
    }
}