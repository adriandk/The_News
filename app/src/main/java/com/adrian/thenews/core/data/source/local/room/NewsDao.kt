package com.adrian.thenews.core.data.source.local.room

import androidx.room.*
import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news where isFavorite = 1")
    fun getBookmarkNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news where newsTitle LIKE '%' || :searchQuery || '%'")
    fun searchData(searchQuery: String): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Update
    fun updateFavoriteNews(news: NewsEntity)
}