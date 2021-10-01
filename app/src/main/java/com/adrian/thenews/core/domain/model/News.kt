package com.adrian.thenews.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val newsId: Int?,
    val newsTitle: String?,
    val sourceNews: String?,
    val newsDescription: String?,
    val url: String?,
    val imageUrl:String?,
    val publishDate: String?,
    val content: String?,
    val isFavorite: Boolean
): Parcelable
