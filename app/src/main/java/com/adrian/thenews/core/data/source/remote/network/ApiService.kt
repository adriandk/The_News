package com.adrian.thenews.core.data.source.remote.network

import com.adrian.thenews.core.data.source.remote.response.ListNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") search: String,
        @Query("apiKey") api: String
    ): ListNewsResponse

}