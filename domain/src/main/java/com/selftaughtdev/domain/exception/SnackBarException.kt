package com.selftaughtdev.domain.exception

import com.selftaughtdev.domain.annotation.ExceptionType

class SnackBarException(
    override val code: Int,
    override val message: String
) : CleanException(code, ExceptionType.SNACK, message)