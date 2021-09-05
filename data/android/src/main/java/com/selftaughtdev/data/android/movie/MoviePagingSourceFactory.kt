package com.selftaughtdev.data.android.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.selftaughtdev.data.common.movies.MovieCacheSource
import com.selftaughtdev.data.common.movies.MovieNetworkSource
import com.selftaughtdev.domain.movie.model.Movie
import javax.inject.Inject

class MoviePagingSourceFactory @Inject constructor(
    private val cacheSource: MovieCacheSource,
    private val networkSource: MovieNetworkSource,
) {

    private val pagingConfig =
        PagingConfig(pageSize = 20, enablePlaceholders = false)

    fun discoverMoviePaging(): Pager<Int, Movie> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                DiscoverMovieSource(
                    cacheSource,
                    networkSource
                )
            })
    }
}