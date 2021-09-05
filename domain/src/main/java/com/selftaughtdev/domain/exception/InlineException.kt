package com.selftaughtdev.domain.exception

import com.selftaughtdev.domain.annotation.ExceptionType
import com.selftaughtdev.domain.base.model.Tag

class InlineException(
    override val code: Int,
    vararg val tags: Tag
) : CleanException(code, ExceptionType.INLINE, null)