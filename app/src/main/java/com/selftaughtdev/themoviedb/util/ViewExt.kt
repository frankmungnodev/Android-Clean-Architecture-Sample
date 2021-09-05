package com.selftaughtdev.themoviedb.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import coil.load
import coil.size.Scale
import com.selftaughtdev.themoviedb.R

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(@DrawableRes resId: Int) = this.load(resId) {
    placeholder(R.drawable.loading_animation)
    error(R.drawable.ic_broken_image)
}

fun ImageView.loadImage(url: Uri) = this.load(url) {
    placeholder(R.drawable.loading_animation)
    error(R.drawable.ic_broken_image)
}

fun ImageView.loadImage(url: String) = this.load(url) {
    placeholder(R.drawable.loading_animation)
    error(R.drawable.ic_broken_image)
}

fun Context.showDialog(
    title: String? = null,
    message: String? = null,
    cancelable: Boolean? = false,
    positiveMessage: String? = null,
    positiveAction: (() -> Unit)? = null,
    negativeMessage: String? = null,
    negativeAction: (() -> Unit)? = null
) {
    AlertDialog.Builder(this).apply {
        title?.let { setTitle(it) }
        cancelable?.let { setCancelable(it) }
        message?.let { setMessage(message) }
        positiveMessage?.let { setPositiveButton(it) { _, _ -> positiveAction?.invoke() } }
        negativeMessage?.let { setNegativeButton(it) { _, _ -> negativeAction?.invoke() } }
    }.create().show()
}

fun Context.showSoftKeyboard(show: Boolean) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    when (show) {
        true -> imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        else -> when (this) {
            is Activity -> imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
            is Fragment -> imm.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
        }
    }
}