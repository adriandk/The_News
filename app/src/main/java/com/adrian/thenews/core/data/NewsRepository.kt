package com.adrian.thenews.core.data

import androidx.lifecycle.LiveData
import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.data.source.remote.RemoteDataSource
import com.adrian.thenews.core.domain.repository.INewsRepository
import com.adrian.thenews.core.utils.AppExecutors

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {
    override fun loadAllNews(): LiveData<Resource<List<NewsEntity>>> {
        TODO("Not yet implemented")
    }

    override fun getBookmarkNews(): LiveData<List<NewsEntity>> {
        TODO("Not yet implemented")
    }

    override fun setBookmarkNews(news: NewsEntity, state: Boolean) {
        TODO("Not yet implemented")
    }
}