package com.adrian.thenews.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("url")
    val url: String?,

    @field:SerializedName("urlToImage")
    val imageUrl: String?,

    @field:SerializedName("publishedAt")
    val publishDate: String?,

    @field:SerializedName("content")
    val content: String?
)