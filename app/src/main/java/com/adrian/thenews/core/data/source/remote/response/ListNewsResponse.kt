package com.adrian.thenews.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListNewsResponse(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("totalResults")
    val total: Int,

    @field:SerializedName("articles")
    val news: List<NewsResponse>
)