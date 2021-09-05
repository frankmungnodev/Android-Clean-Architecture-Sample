package com.selftaughtdev.domain.exception

import java.io.IOException

data class NetworkException constructor(
  val errorBody: String? = null,
  var errorCode: Int = 0
) : IOException()