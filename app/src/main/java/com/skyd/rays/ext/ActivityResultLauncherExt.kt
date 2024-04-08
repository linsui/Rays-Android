package com.skyd.rays.ext

import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityOptionsCompat
import com.skyd.rays.ui.component.showToast

fun <I> ActivityResultLauncher<I>.safeLaunch(
    input: I,
    onError: (Throwable) -> Unit = { it.message?.showToast() },
) {
    runCatching {
        launch(input)
    }.onFailure {
        // May: No Activity found to handle Intent
        it.printStackTrace()
        onError.invoke(it)
    }
}

fun <I> ActivityResultLauncher<I>.safeLaunch(
    input: I,
    options: ActivityOptionsCompat,
    onError: (Throwable) -> Unit = { it.message?.showToast() },
) {
    runCatching {
        launch(input, options)
    }.onFailure {
        // May: No Activity found to handle Intent
        it.printStackTrace()
        onError.invoke(it)
    }
}