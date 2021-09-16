package com.adrian.thenews.core.viewmodel

import androidx.lifecycle.ViewModel
import com.adrian.thenews.core.domain.usecase.NewsUseCase

class BookmarkViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    val bookmarkNews = newsUseCase.getBookmarkNews()
}