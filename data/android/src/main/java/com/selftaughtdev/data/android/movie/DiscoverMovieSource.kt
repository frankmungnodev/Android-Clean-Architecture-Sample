package com.selftaughtdev.data.android.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.selftaughtdev.data.common.movies.MovieCacheSource
import com.selftaughtdev.data.common.movies.MovieNetworkSource
import com.selftaughtdev.domain.DISCOVER_MOVIE
import com.selftaughtdev.domain.movie.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class DiscoverMovieSource constructor(
    private val movieCacheSource: MovieCacheSource,
    private val movieNetworkSource: MovieNetworkSource
) : PagingSource<Int, Movie>() {

    companion object {
        const val STARTING_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val page = params.key ?: STARTING_PAGE
            val itemPerPage = params.loadSize

            val movieList = withContext(Dispatchers.IO) {
                try {
                    val movieFromNetwork = movieNetworkSource.discoverMovies(page)

                    if (params is LoadParams.Refresh) {
                        Timber.d("Delete discover movies")
                        movieCacheSource.deleteDiscoverMovies()
                    }
                    movieCacheSource.insertDiscoverMovies(movieFromNetwork)
                    movieCacheSource.getDiscoverMoviesList(page, itemPerPage, DISCOVER_MOVIE)
                } catch (exception: Exception) {

                    val listFromCache =
                        movieCacheSource.getDiscoverMoviesList(page, itemPerPage, DISCOVER_MOVIE)

                    if (listFromCache.isEmpty()) {
                        throw exception
                    }
                    listFromCache
                }
            }

            val nextKey = if (movieList.isEmpty()) null else page + 1

            return LoadResult.Page(
                data = movieList,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}