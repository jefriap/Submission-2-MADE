package com.jefriap.submission2made.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jefriap.submission2made.core.data.Resource
import com.jefriap.submission2made.core.domain.model.TvShowModel
import com.jefriap.submission2made.core.domain.usecase.FilmCatalogueUseCase

class TvShowViewModel(private val filmCatalogueUseCase: FilmCatalogueUseCase) : ViewModel() {
    fun getTvShows(sort: String): LiveData<Resource<List<TvShowModel>>> {
        return filmCatalogueUseCase.getAllTvShows(sort).asLiveData()
    }
}