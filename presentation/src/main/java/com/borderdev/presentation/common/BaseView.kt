package com.borderdev.presentation.common

import androidx.annotation.StringRes

interface BaseView {
    fun showError(error: Throwable)
}