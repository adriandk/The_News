package com.adrian.thenews.core.data.source.remote.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("source") search: String = "bbc-news",
        @Query("apiKey") api: String
    )

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") search: String,
        @Query("apiKey") api: String
    )

}