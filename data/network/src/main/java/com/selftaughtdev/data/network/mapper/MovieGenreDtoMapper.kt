package com.selftaughtdev.data.network.mapper

import com.selftaughtdev.data.common.mapper.Mapper
import com.selftaughtdev.data.network.dto.model.MovieGenreDto
import com.selftaughtdev.domain.genre.model.MovieGenre
import javax.inject.Inject


class MovieGenreDtoMapper @Inject constructor() : Mapper<MovieGenre, MovieGenreDto> {
    override fun mapToDomain(dto: MovieGenreDto): MovieGenre = MovieGenre(
        dto.id,
        dto.name
    )

    override fun mapToDto(domain: MovieGenre): MovieGenreDto = MovieGenreDto(
        domain.id,
        domain.name
    )
}