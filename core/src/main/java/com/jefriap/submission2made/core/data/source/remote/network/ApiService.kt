package com.jefriap.submission2made.core.data.source.remote.network

import com.jefriap.submission2made.core.data.source.remote.response.MovieResponse
import com.jefriap.submission2made.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getListMovies(
        @Query("api_key") api_key: String
    ): MovieResponse

    @GET("tv/popular")
    suspend fun getListTvShow(
        @Query("api_key") api_key: String
    ): TvShowResponse
}