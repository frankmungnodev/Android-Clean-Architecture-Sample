package com.selftaughtdev.data.network.mapper

import javax.inject.Inject

class MapperFacade @Inject constructor(
    val movieMapper: MovieDtoMapper,
    val movieGenreMapper: MovieGenreDtoMapper,
    val tvGenreMapper: TvGenreDtoMapper
)