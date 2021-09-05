package com.selftaughtdev.domain.exception

import com.selftaughtdev.domain.annotation.ExceptionType
import com.selftaughtdev.domain.base.model.Redirect

class RedirectException(
    override val code: Int,
    val redirect: Redirect
) : CleanException(code, ExceptionType.REDIRECT, null)