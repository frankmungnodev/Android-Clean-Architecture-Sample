package com.selftaughtdev.data.cache.mapper

import com.selftaughtdev.data.cache.room.entity.MovieEntity
import com.selftaughtdev.data.common.mapper.Mapper
import com.selftaughtdev.domain.DISCOVER_MOVIE
import com.selftaughtdev.domain.movie.model.Movie
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() : Mapper<Movie, MovieEntity> {

    var source: String = DISCOVER_MOVIE

    override fun mapToDomain(dto: MovieEntity): Movie = Movie(
        dto.id,
        dto.title,
        dto.originalTitle,
        dto.posterPath,
        dto.backdropPath,
        dto.overview,
        dto.releaseDate,
        dto.rating,
        dto.movieGenreIds
    )

    override fun mapToDto(domain: Movie): MovieEntity = MovieEntity(
        domain.id,
        domain.title,
        domain.originalTitle,
        domain.posterPath,
        domain.backdropPath,
        domain.overview,
        domain.releaseDate,
        domain.rating,
        domain.movieGenreIds,
        source
    )
}