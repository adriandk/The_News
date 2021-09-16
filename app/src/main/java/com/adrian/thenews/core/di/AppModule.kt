package com.adrian.thenews.core.di

import com.adrian.thenews.core.domain.usecase.NewsInteractor
import com.adrian.thenews.core.domain.usecase.NewsUseCase
import com.adrian.thenews.core.viewmodel.BookmarkViewModel
import com.adrian.thenews.core.viewmodel.DetailViewModel
import com.adrian.thenews.core.viewmodel.NewsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}
val viewModelModule = module {
    viewModel { NewsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { BookmarkViewModel(get()) }
}