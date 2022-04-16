package com.submission.filmcatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @ColumnInfo(name = "poster")
    var poster: String? = null,

    @ColumnInfo(name = "backdrop")
    var backdrop: String? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)
