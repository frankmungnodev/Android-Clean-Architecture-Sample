package com.selftaughtdev.data.cache.mapper

import com.selftaughtdev.data.cache.room.entity.MovieGenreEntity
import com.selftaughtdev.data.common.mapper.Mapper
import com.selftaughtdev.domain.genre.model.MovieGenre
import javax.inject.Inject

class MovieGenreEntityMapper @Inject constructor() : Mapper<MovieGenre, MovieGenreEntity> {

    override fun mapToDomain(dto: MovieGenreEntity): MovieGenre = MovieGenre(
        dto.id, dto.name
    )

    override fun mapToDto(domain: MovieGenre): MovieGenreEntity = MovieGenreEntity(
        domain.id, domain.name
    )
}