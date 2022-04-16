package com.jefriap.submission2made.core.domain.model

data class TvShowModel(
    var tvShowId: Int,
    var name: String,
    var overview: String,
    var rating: Double,
    var releaseDate: String,
    var poster: String? = null,
    var backdrop: String? = null,
    var favorite: Boolean = false,
)
