package com.jefriap.submission2made.ui.detail

import androidx.lifecycle.*
import com.jefriap.submission2made.core.data.Resource
import com.jefriap.submission2made.core.domain.model.MovieModel
import com.jefriap.submission2made.core.domain.model.TvShowModel
import com.jefriap.submission2made.core.domain.usecase.FilmCatalogueUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val filmCatalogueUseCase: FilmCatalogueUseCase) : ViewModel() {
    private val itemId = MutableLiveData<Int>()

    fun setSelectedItem(itemId: Int) {
        this.itemId.value = itemId
    }

    var movieItem: LiveData<Resource<MovieModel>> = Transformations.switchMap(itemId) {
        filmCatalogueUseCase.getDetailMovie(it).asLiveData()
    }

    var tvShowItem: LiveData<Resource<TvShowModel>> = Transformations.switchMap(itemId) {
        filmCatalogueUseCase.getDetailTvShow(it).asLiveData()
    }

    fun setFavoriteMovie() {
        val movieResource = movieItem.value
        if (movieResource != null) {
            val movieData = movieResource.data

            if (movieData != null) {
                val newState = !movieData.favorite
                viewModelScope.launch {
                    filmCatalogueUseCase.setMovieFavorite(movieData, newState)
                }
            }
        }
    }

    fun setFavoriteTvShow() {
        val tvResource = tvShowItem.value
        if (tvResource != null) {
            val tvShowData = tvResource.data

            if (tvShowData != null) {
                val newState = !tvShowData.favorite
                viewModelScope.launch {
                    filmCatalogueUseCase.setTvShowFavorite(tvShowData, newState)
                }
            }
        }
    }
}