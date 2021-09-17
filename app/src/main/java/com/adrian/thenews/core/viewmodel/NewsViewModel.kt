package com.adrian.thenews.core.viewmodel

import androidx.lifecycle.ViewModel
import com.adrian.thenews.core.domain.usecase.NewsUseCase

class NewsViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun news(search: String) = newsUseCase.loadAllNews(search)
}