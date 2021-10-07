package com.adrian.thenews.core.data.source.local

import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val newsDao: NewsDao) {

    fun getAllNews(): Flow<List<NewsEntity>> = newsDao.getAllNews()

    fun searchNews(search: String): Flow<List<NewsEntity>> = newsDao.searchData(search)

    fun getBookmarkNews(): Flow<List<NewsEntity>> = newsDao.getBookmarkNews()

    suspend fun insertNews(newsList: List<NewsEntity>) =
        newsDao.insertNews(newsList)

    fun setBookmarkNews(news: NewsEntity, newState: Boolean) {
        news.isFavorite = newState
        newsDao.updateFavoriteNews(news)
    }
}