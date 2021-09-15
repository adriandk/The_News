package com.adrian.thenews.core.data.source.remote.network

import com.adrian.thenews.core.data.source.remote.response.ListNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    fun getNews(
        @Query("source") search: String = "bbc-news",
        @Query("apiKey") api: String
    ): Call<ListNewsResponse>

    @GET("v2/everything")
    fun searchNews(
        @Query("q") search: String,
        @Query("apiKey") api: String
    ): Call<ListNewsResponse>

}