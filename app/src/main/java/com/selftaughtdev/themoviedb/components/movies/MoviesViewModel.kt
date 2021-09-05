package com.selftaughtdev.themoviedb.components.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.selftaughtdev.data.android.movie.MoviePagingSourceFactory
import com.selftaughtdev.domain.movie.model.Movie
import com.selftaughtdev.themoviedb.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviePagingSourceFactory: MoviePagingSourceFactory
) : BaseViewModel() {

    private var _pagingData = MutableLiveData<PagingData<Movie>>()
    val pagingData: LiveData<PagingData<Movie>> get() = _pagingData

    init {
        viewModelScope.launch {
            moviePagingSourceFactory.discoverMoviePaging().flow
                .cachedIn(viewModelScope)
                .collectLatest {
                    _pagingData.postValue(it)
                }
        }
    }
}