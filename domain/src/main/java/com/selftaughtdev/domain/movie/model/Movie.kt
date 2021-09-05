package com.selftaughtdev.domain.movie.model

import com.selftaughtdev.domain.base.model.DomainModel
import com.selftaughtdev.domain.genre.model.MovieGenre

data class Movie(
    val id: Int,
    val title: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val rating: Double?,
    val movieGenreIds: List<Int>?
) : DomainModel() {
    val movieGenre = movieGenreIds?.toMovieGenreList()
    val poster = "https://image.tmdb.org/t/p/w500$posterPath"
    val background = "https://image.tmdb.org/t/p/w500$backdropPath"
}

fun List<Int>.toMovieGenreList(): List<MovieGenre> {

    val list = mutableListOf<MovieGenre>()
    this.forEach { id ->
        list.add(
            when (id) {
                12 -> MovieGenre(id, "Adventure")
                14 -> MovieGenre(id, "Fantasy")
                16 -> MovieGenre(id, "Animation")
                18 -> MovieGenre(id, "Drama")
                27 -> MovieGenre(id, "Horror")
                28 -> MovieGenre(id, "Action")
                35 -> MovieGenre(id, "Comedy")
                36 -> MovieGenre(id, "History")
                37 -> MovieGenre(id, "Western")
                53 -> MovieGenre(id, "Thriller")
                80 -> MovieGenre(id, "Crime")
                99 -> MovieGenre(id, "Documentary")
                878 -> MovieGenre(id, "Science Fiction")
                10402 -> MovieGenre(id, "Music")
                10749 -> MovieGenre(id, "Romance")
                10751 -> MovieGenre(id, "Family")
                10752 -> MovieGenre(id, "War")
                10770 -> MovieGenre(id, "TV Movie")
                9648 -> MovieGenre(id, "Mystery")
                else -> MovieGenre(id, "Action")
            }
        )
    }
    return list
}