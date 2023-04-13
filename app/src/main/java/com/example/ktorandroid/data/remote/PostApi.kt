package com.example.ktorandroid.data.remote

import com.example.ktorandroid.data.dto.PostRequest
import com.example.ktorandroid.data.dto.PostResponse
import com.example.ktorandroid.data.remote.engine.OkHttpclient
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostApi {

    suspend fun getPost(): List<PostResponse>
    suspend fun createPost(postRequest: PostRequest): PostResponse?

    companion object {
        fun create(): PostApi {
            return PostApiImpl(
//                client = HttpClient(Android) {
//                    install(Logging) {
//                        level = LogLevel.ALL
//                    }
//                    install(JsonFeature) {
//                        serializer = KotlinxSerializer()
//                    }
//                }
            client = OkHttpclient
            )
        }
    }
}