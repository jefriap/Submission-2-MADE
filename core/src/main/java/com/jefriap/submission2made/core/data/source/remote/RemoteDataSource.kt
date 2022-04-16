package com.jefriap.submission2made.core.data.source.remote

import com.jefriap.submission2made.core.data.source.remote.network.ApiService
import com.jefriap.submission2made.core.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    /*
    * add your
    * API_KEY
    * in file local.properties
     */
//    val apiKey = BuildConfig.API_KEY
    val apiKey = "674a2084807534d0b078cf3d1eb9cbbe"

    suspend fun getMovies(): Flow<ApiResponse<List<MovieItemResponse>>> {
        return flow {
            try {
                val response = apiService.getListMovies(apiKey)
                val movieList = response.results

                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShows(): Flow<ApiResponse<List<TvShowItemResponse>>> {
        return flow {
            try {
                val response = apiService.getListTvShow(apiKey)
                val movieList = response.results

                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}