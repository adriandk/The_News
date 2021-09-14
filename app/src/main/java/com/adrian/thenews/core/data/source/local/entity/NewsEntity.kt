package com.adrian.thenews.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "newsId")
    var newsId: String,

    @ColumnInfo(name = "newsTitle")
    var newsTitle: String,

    @ColumnInfo(name = "description")
    var newsDescription: String,

    @ColumnInfo(name = "url")
    var url: String?,

    @ColumnInfo(name = "urlToImage")
    var imageUrl: String?,

    @ColumnInfo(name = "publishedAt")
    var publishDate: String?,

    @ColumnInfo(name = "content")
    var content: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean

)