package com.adrian.thenews.core.data

import com.adrian.thenews.core.data.source.local.LocalDataSource
import com.adrian.thenews.core.data.source.remote.RemoteDataSource
import com.adrian.thenews.core.data.source.remote.network.ApiResponse
import com.adrian.thenews.core.data.source.remote.response.NewsResponse
import com.adrian.thenews.core.domain.model.News
import com.adrian.thenews.core.domain.repository.INewsRepository
import com.adrian.thenews.core.utils.AppExecutors
import com.adrian.thenews.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {

    override fun loadAllNews(search: String): Flow<Resource<List<News>>> =
        object: NetworkBoundResource<List<News>, List<NewsResponse>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getAllNews().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<NewsResponse>>> =
                remoteDataSource.getALlNews(search)

            override suspend fun saveCallResult(data: List<NewsResponse>) {
                val newsList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getBookmarkNews(): Flow<List<News>> {
        return localDataSource.getBookmarkNews().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setBookmarkNews(news: News, state: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute {
            localDataSource.setBookmarkNews(newsEntity, state)
        }
    }

}