package com.adrian.thenews.core.utils

import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.data.source.remote.response.NewsResponse

object DataMapper {

    fun mapResponseToEntities(input: List<NewsResponse>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map { newsData ->
            val news = NewsEntity(
                newsId = null,
                newsTitle = newsData.title,
                sourceNews = newsData.source.sourceName,
                newsDescription = newsData.description,
                url = newsData.url,
                imageUrl = newsData.imageUrl,
                publishDate = newsData.publishDate,
                content = newsData.content,
                isFavorite = false
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapResponseToEntity(input: NewsResponse) = NewsEntity(
        newsId = null,
        newsTitle = input.title,
        sourceNews = input.source.sourceName,
        newsDescription = input.description,
        url = input.url,
        imageUrl = input.imageUrl,
        publishDate = input.publishDate,
        content = input.content,
        isFavorite = false
    )
}