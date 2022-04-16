package com.jefriap.submission2made.favorite.di

import com.jefriap.submission2made.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel {
        FavoriteViewModel(get())
    }
}