package com.selftaughtdev.data.cache.mapper

import com.selftaughtdev.data.cache.room.entity.TVGenreEntity
import com.selftaughtdev.data.common.mapper.Mapper
import com.selftaughtdev.domain.genre.model.TVGenre
import javax.inject.Inject

class TVGenreEntityMapper @Inject constructor() : Mapper<TVGenre, TVGenreEntity> {

    override fun mapToDomain(dto: TVGenreEntity): TVGenre = TVGenre(
        dto.id, dto.name
    )

    override fun mapToDto(domain: TVGenre): TVGenreEntity = TVGenreEntity(
        domain.id, domain.name
    )
}