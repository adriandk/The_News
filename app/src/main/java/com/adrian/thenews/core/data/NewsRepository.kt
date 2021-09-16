package com.adrian.thenews.core.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.adrian.thenews.core.data.source.local.LocalDataSource
import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.data.source.remote.RemoteDataSource
import com.adrian.thenews.core.data.source.remote.network.ApiResponse
import com.adrian.thenews.core.data.source.remote.response.NewsResponse
import com.adrian.thenews.core.domain.repository.INewsRepository
import com.adrian.thenews.core.utils.AppExecutors
import com.adrian.thenews.core.utils.DataMapper

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {

    override fun loadAllNews(): LiveData<Resource<PagedList<NewsEntity>>> =
        object : NetworkBoundResource<PagedList<NewsEntity>, List<NewsResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<NewsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllNews(), config).build()
            }

            override fun shouldFetch(data: PagedList<NewsEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<NewsResponse>>> =
                remoteDataSource.getAllNews()

            override fun saveCallResult(data: List<NewsResponse>) {
                val newsList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertNews(newsList)
            }

        }.asLiveData()

    override fun getBookmarkNews(): LiveData<PagedList<NewsEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkNews(), config).build()
    }

    override fun setBookmarkNews(news: NewsEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setBookmarkNews(news, state)
        }
    }


}