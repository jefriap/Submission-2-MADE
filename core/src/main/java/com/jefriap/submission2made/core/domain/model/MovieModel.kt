package com.jefriap.submission2made.core.domain.model

data class MovieModel(
    var movieId: Int,
    var title: String,
    var overview: String,
    var rating: Double,
    var poster: String? = null,
    var backdrop: String? = null,
    var releaseDate: String,
    var favorite: Boolean = false,
)
