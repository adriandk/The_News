package com.adrian.thenews.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.adrian.thenews.core.data.source.local.entity.NewsEntity

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): DataSource.Factory<Int, NewsEntity>

    @Query("SELECT * FROM news where isFavorite = 1")
    fun getBookmarkNews(): DataSource.Factory<Int, NewsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(news: List<NewsEntity>)

    @Update
    fun updateFavoriteNews(news: NewsEntity)
}