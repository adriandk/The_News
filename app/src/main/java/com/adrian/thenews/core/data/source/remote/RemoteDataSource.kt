package com.adrian.thenews.core.data.source.remote

import com.adrian.thenews.BuildConfig.API_KEY
import com.adrian.thenews.core.data.source.remote.network.ApiResponse
import com.adrian.thenews.core.data.source.remote.network.ApiService
import com.adrian.thenews.core.data.source.remote.response.NewsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllNews(): Flow<ApiResponse<List<NewsResponse>>> {
        return flow {
            try {
                val response = apiService.getNews("", API_KEY)
            } catch (e: Exception) {

            }
        }
    }
}