package com.adrian.thenews.core.di

import android.content.Context
import com.adrian.thenews.core.data.NewsRepository
import com.adrian.thenews.core.data.source.local.LocalDataSource
import com.adrian.thenews.core.data.source.local.room.NewsDatabase
import com.adrian.thenews.core.data.source.remote.RemoteDataSource
import com.adrian.thenews.core.data.source.remote.network.ApiConfig
import com.adrian.thenews.core.domain.repository.INewsRepository
import com.adrian.thenews.core.domain.usecase.NewsInteractor
import com.adrian.thenews.core.domain.usecase.NewsUseCase
import com.adrian.thenews.core.utils.AppExecutors

object Injection {

    private fun provideRepository(context: Context): INewsRepository {
        val database = NewsDatabase.getDatabase(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.newsDao())
        val appExecutors = AppExecutors()

        return NewsRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideNewsUseCase(context: Context): NewsUseCase {
        val repository = provideRepository(context)
        return NewsInteractor(repository)
    }

}