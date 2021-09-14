package com.adrian.thenews.core.di

import androidx.room.Room
import com.adrian.thenews.core.data.NewsRepository
import com.adrian.thenews.core.data.source.local.LocalDataSource
import com.adrian.thenews.core.data.source.local.room.NewsDatabase
import com.adrian.thenews.core.data.source.remote.RemoteDataSource
import com.adrian.thenews.core.data.source.remote.network.ApiService
import com.adrian.thenews.core.domain.repository.INewsRepository
import com.adrian.thenews.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<NewsDatabase>().newsDao() }
    single {
        val passpharse: ByteArray = SQLiteDatabase.getBytes("thenews".toCharArray())
        val factory = SupportFactory(passpharse)
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java, "Thenews.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModuleDeclaration = module {
    single {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repository = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<INewsRepository> {
        NewsRepository(
            get(),
            get(),
            get()
        )
    }
}