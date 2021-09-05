package com.selftaughtdev.domain.genre.model

import com.selftaughtdev.domain.base.model.DomainModel

data class TVGenre(
    val id: Int,
    val name: String
) : DomainModel()