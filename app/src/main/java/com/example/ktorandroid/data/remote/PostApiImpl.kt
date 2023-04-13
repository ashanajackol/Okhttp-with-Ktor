package com.example.ktorandroid.data.remote

import com.example.ktorandroid.data.dto.PostRequest
import com.example.ktorandroid.data.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.*

class PostApiImpl(private val client: HttpClient) : PostApi {

    override suspend fun getPost(): List<PostResponse> {
        return try {
            client.get{url(HttpRoutes.POST_REQUEST)}
        } catch (e: RedirectResponseException) {
            //3xx - response
            println("Error ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            //4xx - response
            println("Error ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            //5xx - response
            println("Error ${e.response.status.description}")
            emptyList()
        } catch (e: java.lang.Exception) {
            //general exception
            println("Error ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse>{
                url(HttpRoutes.POST_REQUEST)
                body = postRequest
            }
        } catch (e: RedirectResponseException) {
            //3xx - response
            println("Error ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            //4xx - response
            println("Error ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            //5xx - response
            println("Error ${e.response.status.description}")
            null
        } catch (e: java.lang.Exception) {
            //general exception
            println("Error ${e.message}")
            null
        }
    }
}