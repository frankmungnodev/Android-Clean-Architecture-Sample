package com.selftaughtdev.domain

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
  fun main(): CoroutineDispatcher
  fun io(): CoroutineDispatcher
  fun default(): CoroutineDispatcher
  fun unconfined(): CoroutineDispatcher
}