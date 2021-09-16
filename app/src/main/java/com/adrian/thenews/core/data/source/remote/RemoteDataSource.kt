package com.adrian.thenews.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adrian.thenews.BuildConfig.API_KEY
import com.adrian.thenews.core.data.source.remote.network.ApiResponse
import com.adrian.thenews.core.data.source.remote.network.ApiService
import com.adrian.thenews.core.data.source.remote.response.ListNewsResponse
import com.adrian.thenews.core.data.source.remote.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {

    fun getAllNews(): LiveData<ApiResponse<List<NewsResponse>>> {
        val newsList = MutableLiveData<ApiResponse<List<NewsResponse>>>()

        apiService.getNews("bbc-news", API_KEY)
            .enqueue(object : Callback<ListNewsResponse> {
                override fun onResponse(
                    call: Call<ListNewsResponse>,
                    response: Response<ListNewsResponse>
                ) {
                    newsList.value =
                        ApiResponse.Success(response.body()!!.news)
                }

                override fun onFailure(call: Call<ListNewsResponse>, t: Throwable) {
                    Log.e("RemoteDataSource", t.message.toString())
                }
            })
        return newsList
    }

    fun getSearchNews(search: String): LiveData<ApiResponse<List<NewsResponse>>> {
        val newsList = MutableLiveData<ApiResponse<List<NewsResponse>>>()

        apiService.searchNews(search, API_KEY)
            .enqueue(object : Callback<ListNewsResponse> {
                override fun onResponse(
                    call: Call<ListNewsResponse>,
                    response: Response<ListNewsResponse>
                ) {
                    newsList.value =
                        ApiResponse.Success(response.body()?.news as List<NewsResponse>)
                }

                override fun onFailure(call: Call<ListNewsResponse>, t: Throwable) {
                    Log.e("RemoteDataSource", t.message.toString())
                }
            })
        return newsList
    }

}