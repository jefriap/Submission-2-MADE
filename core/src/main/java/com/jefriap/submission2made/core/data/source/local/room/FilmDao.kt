package com.jefriap.submission2made.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.submission.filmcatalogue.data.local.entity.MovieEntity
import com.submission.filmcatalogue.data.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovies(query: SupportSQLiteQuery): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getTvShows(query: SupportSQLiteQuery): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: List<TvShowEntity>)

    @Transaction
    @Query("SELECT * FROM movieentity WHERE movieId = :movieId")
    fun getDetailMovie(movieId: Int): Flow<MovieEntity>

    @Transaction
    @Query("SELECT * FROM tvshowentity WHERE tvShowId = :tvShowId")
    fun getDetailTvShow(tvShowId: Int): Flow<TvShowEntity>

    @Update
    suspend fun updateMovieFavorite(movie: MovieEntity)

    @Update
    suspend fun updateTvShowFavorite(tvShow: TvShowEntity)

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getFavoriteMovies(query: SupportSQLiteQuery): Flow<List<MovieEntity>>

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getFavoriteTvShows(query: SupportSQLiteQuery): Flow<List<TvShowEntity>>
}