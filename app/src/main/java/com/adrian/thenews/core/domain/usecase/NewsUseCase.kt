package com.adrian.thenews.core.domain.usecase

import androidx.lifecycle.LiveData
import com.adrian.thenews.core.data.Resource
import com.adrian.thenews.core.data.source.local.entity.NewsEntity

interface NewsUseCase {
    fun loadAllNews(): LiveData<Resource<List<NewsEntity>>>
    fun getBookmarkNews(): LiveData<List<NewsEntity>>
    fun setBookmarkNews(news: NewsEntity, state: Boolean)
}