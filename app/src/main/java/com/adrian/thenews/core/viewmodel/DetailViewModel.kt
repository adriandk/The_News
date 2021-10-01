package com.adrian.thenews.core.viewmodel

import androidx.lifecycle.ViewModel
import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.domain.model.News
import com.adrian.thenews.core.domain.usecase.NewsUseCase

class DetailViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun setBookmarkNews(news: News, newStatus: Boolean) =
        newsUseCase.setBookmarkNews(news, newStatus)
}