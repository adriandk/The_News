package com.adrian.thenews.core.domain.usecase

import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.domain.repository.INewsRepository

class NewsInteractor(private val newsRepository: INewsRepository) : NewsUseCase {

    override fun loadAllNews(search: String) = newsRepository.loadAllNews(search)

    override fun getBookmarkNews() = newsRepository.getBookmarkNews()

    override fun setBookmarkNews(news: NewsEntity, state: Boolean) {
        newsRepository.setBookmarkNews(news, state)
    }
}