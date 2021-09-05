package com.selftaughtdev.data.network.mapper

import com.selftaughtdev.data.common.mapper.Mapper
import com.selftaughtdev.data.network.dto.model.TVGenreDto
import com.selftaughtdev.domain.genre.model.TVGenre
import javax.inject.Inject

class TvGenreDtoMapper @Inject constructor() : Mapper<TVGenre, TVGenreDto> {
    override fun mapToDomain(dto: TVGenreDto): TVGenre = TVGenre(
        dto.id,
        dto.name
    )

    override fun mapToDto(domain: TVGenre): TVGenreDto = TVGenreDto(
        domain.id,
        domain.name
    )
}