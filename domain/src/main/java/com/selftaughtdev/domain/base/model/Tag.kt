package com.selftaughtdev.domain.base.model

import com.selftaughtdev.domain.annotation.TagName

data class Tag(@TagName val name: String, val message: String?)