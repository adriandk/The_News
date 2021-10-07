package com.adrian.thenews.core.domain.usecase

import com.adrian.thenews.core.data.Resource
import com.adrian.thenews.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun loadAllNews(search: String): Flow<Resource<List<News>>>
    fun searchNews(search: String): Flow<List<News>>
    fun getBookmarkNews(): Flow<List<News>>
    fun setBookmarkNews(news: News, state: Boolean)
}