package com.jefriap.submission2made.core.data

import com.jefriap.submission2made.core.data.source.local.LocalDataSource
import com.jefriap.submission2made.core.data.source.remote.RemoteDataSource
import com.jefriap.submission2made.core.data.source.remote.response.ApiResponse
import com.jefriap.submission2made.core.data.source.remote.response.MovieItemResponse
import com.jefriap.submission2made.core.data.source.remote.response.TvShowItemResponse
import com.jefriap.submission2made.core.domain.model.MovieModel
import com.jefriap.submission2made.core.domain.model.TvShowModel
import com.jefriap.submission2made.core.domain.repository.IFilmCatalogueRepository
import com.jefriap.submission2made.core.utils.AppExecutors
import com.jefriap.submission2made.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmCatalogueRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IFilmCatalogueRepository {

    override fun getAllMovies(sort: String): Flow<Resource<List<MovieModel>>> {
        return object : NetworkBoundResource<List<MovieModel>, List<MovieItemResponse>>() {
            override fun loadFromDB(): Flow<List<MovieModel>> {
                return localDataSource.getAllMovies(sort).map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItemResponse>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieItemResponse>) {
                val movieList = DataMapper.mapMovieResponseToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()
    }

    override fun getAllTvShows(sort: String): Flow<Resource<List<TvShowModel>>> {
        return object : NetworkBoundResource<List<TvShowModel>, List<TvShowItemResponse>>() {
            override fun loadFromDB(): Flow<List<TvShowModel>> {
                return localDataSource.getAllTvShows(sort).map {
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShowModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowItemResponse>>> {
                return remoteDataSource.getTvShows()
            }

            override suspend fun saveCallResult(data: List<TvShowItemResponse>) {
                val tvShowList = DataMapper.mapTvShowResponseToEntities(data)
                localDataSource.insertTvShows(tvShowList)
            }
        }.asFlow()
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<MovieModel>> {
        return object : NetworkBoundResource<MovieModel, List<MovieItemResponse>>() {
            override fun loadFromDB(): Flow<MovieModel> {
                return localDataSource.getDetailMovie(movieId).map {
                    MovieModel(
                        movieId = it.movieId,
                        title = it.title,
                        overview = it.overview,
                        rating = it.rating,
                        poster = it.poster,
                        backdrop = it.backdrop,
                        releaseDate = it.releaseDate,
                        favorite = it.favorite
                    )
                }
            }

            override fun shouldFetch(data: MovieModel?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItemResponse>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieItemResponse>) {
                val movieList = DataMapper.mapMovieResponseToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()
    }

    override fun getDetailTvShow(tvShowId: Int): Flow<Resource<TvShowModel>> {
        return object : NetworkBoundResource<TvShowModel, List<TvShowItemResponse>>() {
            override fun loadFromDB(): Flow<TvShowModel> {
                return localDataSource.getDetailTvShow(tvShowId).map {
                    TvShowModel(
                        tvShowId = it.tvShowId,
                        name = it.name,
                        overview = it.overview,
                        rating = it.rating,
                        poster = it.poster,
                        backdrop = it.backdrop,
                        releaseDate = it.releaseDate,
                        favorite = it.favorite
                    )
                }
            }

            override fun shouldFetch(data: TvShowModel?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowItemResponse>>> {
                return remoteDataSource.getTvShows()
            }

            override suspend fun saveCallResult(data: List<TvShowItemResponse>) {
                val tvShowList = DataMapper.mapTvShowResponseToEntities(data)
                localDataSource.insertTvShows(tvShowList)
            }
        }.asFlow()
    }

    override fun getFavoriteMovies(sort: String): Flow<List<MovieModel>> {
        return localDataSource.getAllFavoriteMovies(sort).map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun getFavoritesTvShows(sort: String): Flow<List<TvShowModel>> {
        return localDataSource.getAllFavoriteTvShows(sort).map {
            DataMapper.mapTvShowEntitiesToDomain(it)
        }
    }

    override suspend fun setMovieFavorite(movie: MovieModel, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntities(movie)
        localDataSource.setMovieFavorite(movieEntity, state)
    }

    override suspend fun setTvShowFavorite(tvShow: TvShowModel, state: Boolean) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntities(tvShow)
        localDataSource.setTvShowFavorite(tvShowEntity, state)
    }

}