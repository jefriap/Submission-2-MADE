package com.jefriap.submission2made.core.utils

import com.jefriap.submission2made.core.data.source.remote.response.MovieItemResponse
import com.jefriap.submission2made.core.data.source.remote.response.TvShowItemResponse
import com.jefriap.submission2made.core.domain.model.MovieModel
import com.jefriap.submission2made.core.domain.model.TvShowModel
import com.submission.filmcatalogue.data.local.entity.MovieEntity
import com.submission.filmcatalogue.data.local.entity.TvShowEntity

object DataMapper {

    fun mapMovieResponseToEntities(input: List<MovieItemResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                rating = it.voteAverage,
                releaseDate = it.releaseDate,
                poster = it.posterPath,
                backdrop = it.backdropPath,
                favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvShowResponseToEntities(input: List<TvShowItemResponse>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                tvShowId = it.id,
                name = it.name,
                overview = it.overview,
                rating = it.voteAverage,
                releaseDate = it.firstAirDate,
                poster = it.posterPath,
                backdrop = it.backdropPath,
                favorite = false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<MovieModel> {
        return input.map {
            MovieModel(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                rating = it.rating,
                releaseDate = it.releaseDate,
                poster = it.poster,
                backdrop = it.backdrop,
                favorite = it.favorite
            )
        }
    }

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShowModel> {
        return input.map {
            TvShowModel(
                tvShowId = it.tvShowId,
                name = it.name,
                overview = it.overview,
                rating = it.rating,
                releaseDate = it.releaseDate,
                poster = it.poster,
                backdrop = it.backdrop,
                favorite = it.favorite
            )
        }
    }

    fun mapMovieDomainToEntities(input: MovieModel): MovieEntity {
        return  MovieEntity(
            movieId = input.movieId,
            title = input.title,
            overview = input.overview,
            rating = input.rating,
            releaseDate = input.releaseDate,
            poster = input.poster,
            backdrop = input.backdrop,
            favorite = input.favorite
        )
    }

    fun mapTvShowDomainToEntities(input: TvShowModel): TvShowEntity {
        return TvShowEntity(
            tvShowId = input.tvShowId,
            name = input.name,
            overview = input.overview,
            rating = input.rating,
            releaseDate = input.releaseDate,
            poster = input.poster,
            backdrop = input.backdrop,
            favorite = input.favorite
        )
    }
}