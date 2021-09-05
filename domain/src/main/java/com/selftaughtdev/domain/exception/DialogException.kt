package com.selftaughtdev.domain.exception

import com.selftaughtdev.domain.annotation.ExceptionType
import com.selftaughtdev.domain.base.model.Dialog

class DialogException(
    override val code: Int,
    val dialog: Dialog
) : CleanException(code, ExceptionType.DIALOG, null)