package com.jefriap.submission2made.core.domain.usecase

import com.jefriap.submission2made.core.data.Resource
import com.jefriap.submission2made.core.domain.model.MovieModel
import com.jefriap.submission2made.core.domain.model.TvShowModel
import com.jefriap.submission2made.core.domain.repository.IFilmCatalogueRepository
import kotlinx.coroutines.flow.Flow

class FilmCatalogueInteractor(private val iFilmCatalogueRepository: IFilmCatalogueRepository): FilmCatalogueUseCase {
    override fun getAllMovies(sort: String): Flow<Resource<List<MovieModel>>> {
        return iFilmCatalogueRepository.getAllMovies(sort)
    }

    override fun getAllTvShows(sort: String): Flow<Resource<List<TvShowModel>>> {
        return iFilmCatalogueRepository.getAllTvShows(sort)
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<MovieModel>> {
        return iFilmCatalogueRepository.getDetailMovie(movieId)
    }

    override fun getDetailTvShow(tvShowId: Int): Flow<Resource<TvShowModel>> {
        return iFilmCatalogueRepository.getDetailTvShow(tvShowId)
    }

    override fun getFavoriteMovies(sort: String): Flow<List<MovieModel>> {
        return iFilmCatalogueRepository.getFavoriteMovies(sort)
    }

    override fun getFavoritesTvShows(sort: String): Flow<List<TvShowModel>> {
        return iFilmCatalogueRepository.getFavoritesTvShows(sort)
    }

    override suspend fun setMovieFavorite(movie: MovieModel, state: Boolean) {
        return iFilmCatalogueRepository.setMovieFavorite(movie, state)
    }

    override suspend fun setTvShowFavorite(tvShow: TvShowModel, state: Boolean) {
        return iFilmCatalogueRepository.setTvShowFavorite(tvShow, state)
    }
}