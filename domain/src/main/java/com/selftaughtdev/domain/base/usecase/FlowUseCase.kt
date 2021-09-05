package com.selftaughtdev.domain.base.usecase

import com.selftaughtdev.domain.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<I, O> constructor(
    private val dispatcherProvider: DispatcherProvider
) {

    fun execute(params: I): Flow<O> {
        return provide(params).flowOn(dispatcherProvider.io())
    }

    protected abstract fun provide(params: I): Flow<O>

}