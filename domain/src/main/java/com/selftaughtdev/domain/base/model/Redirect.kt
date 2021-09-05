package com.selftaughtdev.domain.base.model

import com.selftaughtdev.domain.annotation.Redirect

data class Redirect(@Redirect val redirect: Int, val redirectObject: Any? = null)