package com.adrian.thenews.core.data.source.local

import androidx.paging.DataSource
import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.data.source.local.room.NewsDao

class LocalDataSource(private val newsDao: NewsDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(newsDao: NewsDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(newsDao)
            }
    }

    fun getAllNews(): DataSource.Factory<Int, NewsEntity> = newsDao.getAllNews()

    fun getBookmarkNews(): DataSource.Factory<Int, NewsEntity> = newsDao.getBookmarkNews()

    fun insertNews(newsList: List<NewsEntity>) {
        newsDao.insertNews(newsList)
    }

    fun setBookmarkNews(news: NewsEntity, newState: Boolean) {
        news.isFavorite = newState
        newsDao.updateFavoriteNews(news)
    }
}