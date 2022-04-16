package com.jefriap.submission2made.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jefriap.submission2made.core.domain.model.MovieModel
import com.jefriap.submission2made.core.domain.model.TvShowModel
import com.jefriap.submission2made.core.domain.usecase.FilmCatalogueUseCase

class FavoriteViewModel(private val filmCatalogueUseCase: FilmCatalogueUseCase) : ViewModel() {

    fun getFavoriteMovies(sort: String): LiveData<List<MovieModel>> {
        return filmCatalogueUseCase.getFavoriteMovies(sort).asLiveData()
    }

    fun getFavoriteTvShows(sort: String): LiveData<List<TvShowModel>> {
        return filmCatalogueUseCase.getFavoritesTvShows(sort).asLiveData()
    }
}