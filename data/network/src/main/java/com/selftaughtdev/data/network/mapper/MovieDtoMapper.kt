package com.selftaughtdev.data.network.mapper

import com.selftaughtdev.data.common.mapper.Mapper
import com.selftaughtdev.data.network.dto.model.MovieDto
import com.selftaughtdev.domain.movie.model.Movie
import javax.inject.Inject

class MovieDtoMapper @Inject constructor() : Mapper<Movie, MovieDto> {

    override fun mapToDomain(dto: MovieDto): Movie = Movie(
        dto.id,
        dto.title,
        dto.originalTitle,
        dto.posterPath,
        dto.backdropPath,
        dto.overview,
        dto.releaseDate,
        dto.rating,
        dto.movieGenresIds
    )

    override fun mapToDto(domain: Movie): MovieDto = MovieDto(
        domain.id,
        domain.title,
        domain.originalTitle,
        domain.posterPath,
        domain.backdropPath,
        domain.overview,
        domain.releaseDate,
        domain.rating,
        domain.movieGenreIds
    )
}