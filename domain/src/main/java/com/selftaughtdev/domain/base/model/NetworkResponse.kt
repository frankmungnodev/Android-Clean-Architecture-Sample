package com.selftaughtdev.domain.base.model

sealed class NetworkResponse<out T : Any, out U : Any> {

    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    data class Error(val throwable: Throwable) : NetworkResponse<Nothing, Nothing>()
}
