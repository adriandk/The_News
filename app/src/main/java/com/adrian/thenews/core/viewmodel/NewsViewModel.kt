package com.adrian.thenews.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adrian.thenews.core.domain.usecase.NewsUseCase

class NewsViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun news(search: String) = newsUseCase.loadAllNews(search).asLiveData()
    fun searchNews(search: String) = newsUseCase.searchNews(search).asLiveData()
}