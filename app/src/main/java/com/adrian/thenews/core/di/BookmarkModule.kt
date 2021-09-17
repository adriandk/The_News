package com.adrian.thenews.core.di

import com.adrian.thenews.core.viewmodel.BookmarkViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarkModule = module {
    viewModel { BookmarkViewModel(get()) }
}