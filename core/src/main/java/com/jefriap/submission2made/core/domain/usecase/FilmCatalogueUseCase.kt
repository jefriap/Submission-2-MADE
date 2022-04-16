package com.jefriap.submission2made.core.domain.usecase

import com.jefriap.submission2made.core.data.Resource
import com.jefriap.submission2made.core.domain.model.MovieModel
import com.jefriap.submission2made.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow

interface FilmCatalogueUseCase {

    fun getAllMovies(sort: String): Flow<Resource<List<MovieModel>>>

    fun getAllTvShows(sort: String): Flow<Resource<List<TvShowModel>>>

    fun getDetailMovie(movieId: Int): Flow<Resource<MovieModel>>

    fun getDetailTvShow(tvShowId: Int): Flow<Resource<TvShowModel>>

    fun getFavoriteMovies(sort: String): Flow<List<MovieModel>>

    fun getFavoritesTvShows(sort: String): Flow<List<TvShowModel>>

    suspend fun setMovieFavorite(movie: MovieModel, state: Boolean)

    suspend fun setTvShowFavorite(tvShow: TvShowModel, state: Boolean)
}