package com.adrian.thenews.core.data

import androidx.lifecycle.LiveData
import com.adrian.thenews.core.data.source.local.LocalDataSource
import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.data.source.remote.RemoteDataSource
import com.adrian.thenews.core.data.source.remote.network.ApiResponse
import com.adrian.thenews.core.data.source.remote.response.NewsResponse
import com.adrian.thenews.core.domain.model.News
import com.adrian.thenews.core.domain.repository.INewsRepository
import com.adrian.thenews.core.utils.AppExecutors
import com.adrian.thenews.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {

    override fun loadAllNews(search: String): Flow<Resource<List<News>>> =
        object: NetworkBoundResource<List<News>, List<NewsResponse>>() {
            override fun loadFromDB(): Flow<List<News>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<News>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<List<NewsResponse>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<NewsResponse>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun getBookmarkNews(): Flow<List<News>> {
        TODO("Not yet implemented")
    }

    override fun setBookmarkNews(news: News, state: Boolean) {
        TODO("Not yet implemented")
    }

}