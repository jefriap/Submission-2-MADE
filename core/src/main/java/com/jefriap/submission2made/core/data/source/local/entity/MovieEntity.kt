package com.submission.filmcatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "poster")
    var poster: String? = null,

    @ColumnInfo(name = "backdrop")
    var backdrop: String? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)
