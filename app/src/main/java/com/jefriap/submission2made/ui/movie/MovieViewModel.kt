package com.jefriap.submission2made.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jefriap.submission2made.core.data.Resource
import com.jefriap.submission2made.core.domain.model.MovieModel
import com.jefriap.submission2made.core.domain.usecase.FilmCatalogueUseCase

class MovieViewModel(private val filmCatalogueUseCase: FilmCatalogueUseCase) : ViewModel() {
    fun getMovies(sort: String): LiveData<Resource<List<MovieModel>>> {
        return filmCatalogueUseCase.getAllMovies(sort).asLiveData()
    }
}