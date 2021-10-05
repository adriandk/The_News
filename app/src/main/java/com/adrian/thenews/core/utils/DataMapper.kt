package com.adrian.thenews.core.utils

import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.data.source.remote.response.NewsResponse
import com.adrian.thenews.core.domain.model.News

object DataMapper {

    fun mapResponseToEntities(input: List<NewsResponse>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map { newsData ->
            val news = NewsEntity(
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

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                newsTitle = it.newsTitle,
                sourceNews = it.sourceNews,
                newsDescription = it.newsDescription,
                url = it.url,
                imageUrl = it.imageUrl,
                publishDate = it.publishDate,
                content = it.content,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: News) = NewsEntity(
        newsTitle = input.newsTitle,
        sourceNews = input.sourceNews,
        newsDescription = input.newsDescription,
        url = input.url,
        imageUrl = input.imageUrl,
        publishDate = input.publishDate,
        content = input.content,
        isFavorite = input.isFavorite
    )
}