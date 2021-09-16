package com.adrian.thenews.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.adrian.thenews.core.data.Resource
import com.adrian.thenews.core.data.source.local.entity.NewsEntity

interface NewsUseCase {
    fun loadAllNews(): LiveData<Resource<PagedList<NewsEntity>>>
    fun getBookmarkNews(): LiveData<PagedList<NewsEntity>>
    fun setBookmarkNews(news: NewsEntity, state: Boolean)
}