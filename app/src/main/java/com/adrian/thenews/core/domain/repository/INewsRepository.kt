package com.adrian.thenews.core.domain.repository

import com.adrian.thenews.core.data.Resource
import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun loadAllNews(search: String): Flow<Resource<List<News>>>
    fun getBookmarkNews(): Flow<List<News>>
    fun setBookmarkNews(news: News, state: Boolean)
}