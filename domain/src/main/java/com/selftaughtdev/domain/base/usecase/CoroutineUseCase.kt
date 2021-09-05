package com.selftaughtdev.domain.base.usecase

import com.selftaughtdev.domain.DispatcherProvider
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<Input, Output> constructor(
  private val dispatcherProvider: DispatcherProvider
) {
  suspend fun execute(input: Input): Output {
    return withContext(dispatcherProvider.io()) {
      provide(input)
    }
  }

  protected abstract suspend fun provide(input: Input): Output
}