package com.jefriap.submission2made.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val RATING = "Rating"
    const val TITLE = "Title"
    const val NEWEST = "Newest"
    const val RANDOM = "Random"

    fun getSortedQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieentity ")
        when (filter) {
            RATING -> {
                simpleQuery.append("ORDER BY rating DESC")
            }
            NEWEST -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
            TITLE -> {
                simpleQuery.append("ORDER BY title ASC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTvShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tvshowentity ")
        when (filter) {
            RATING -> {
                simpleQuery.append("ORDER BY rating DESC")
            }
            NEWEST -> {
                simpleQuery.append("ORDER BY release_date DESC")
            }
            TITLE -> {
                simpleQuery.append("ORDER BY name ASC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryFavoriteMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery =
            StringBuilder().append("SELECT * FROM movieentity where favorite = 1 ")
        when (filter) {
            RATING -> {
                simpleQuery.append("ORDER BY rating DESC")
            }
            NEWEST -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
            TITLE -> {
                simpleQuery.append("ORDER BY title ASC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryFavoriteTvShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery =
            StringBuilder().append("SELECT * FROM tvshowentity where favorite = 1 ")
        when (filter) {
            RATING -> {
                simpleQuery.append("ORDER BY rating DESC")
            }
            NEWEST -> {
                simpleQuery.append("ORDER BY release_date DESC")
            }
            TITLE -> {
                simpleQuery.append("ORDER BY name ASC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}