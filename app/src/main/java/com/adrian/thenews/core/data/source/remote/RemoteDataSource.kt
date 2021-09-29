package com.adrian.thenews.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adrian.thenews.BuildConfig.API_KEY
import com.adrian.thenews.core.data.source.remote.network.ApiResponse
import com.adrian.thenews.core.data.source.remote.network.ApiService
import com.adrian.thenews.core.data.source.remote.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception


class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getALlNews(search: String): Flow<ApiResponse<List<NewsResponse>>> {
        return flow {
            try {
                val response = apiService.getNews(search, API_KEY)
                val dataArray = response.news

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.news))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                Log.e("remote data source", "failed")
                emit(ApiResponse.Error(e.toString()))
                Log.e("Remote Data", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}