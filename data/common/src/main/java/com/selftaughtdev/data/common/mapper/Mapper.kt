package com.selftaughtdev.data.common.mapper

import com.selftaughtdev.domain.base.model.DomainModel

interface Mapper<DOMAIN : DomainModel, DTO : DtoModel> {

    fun mapToDomain(dto: DTO): DOMAIN

    fun mapToDto(domain: DOMAIN): DTO
}