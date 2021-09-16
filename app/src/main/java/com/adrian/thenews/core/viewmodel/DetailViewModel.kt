package com.adrian.thenews.core.viewmodel

import androidx.lifecycle.ViewModel
import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.domain.usecase.NewsUseCase

class DetailViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun setBookmarkNews(news: NewsEntity, newStatus: Boolean) =
        newsUseCase.setBookmarkNews(news, newStatus)
}