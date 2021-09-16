package com.adrian.thenews.core.viewmodel

import androidx.lifecycle.ViewModel
import com.adrian.thenews.core.domain.usecase.NewsUseCase

class NewsViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    val news = newsUseCase.loadAllNews()
}