package com.selftaughtdev.themoviedb.features.home

import androidx.lifecycle.viewModelScope
import com.selftaughtdev.domain.exception.AlertException
import com.selftaughtdev.domain.genre.usecase.MovieGenresUseCase
import com.selftaughtdev.themoviedb.base.BaseViewModel
import com.selftaughtdev.themoviedb.exception.GlobalErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieGenresUseCase: MovieGenresUseCase,
    private val globalErrorHandler: GlobalErrorHandler
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            try {
                movieGenresUseCase.execute(MovieGenresUseCase.Params())
            } catch (t: Throwable) {
                val message = globalErrorHandler.getMessageForUser(t)
                setThrowable(AlertException(-1, message))
            }
        }
    }
}