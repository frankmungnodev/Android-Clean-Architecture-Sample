package com.selftaughtdev.themoviedb.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (imgUrl != null) {
        imgView.loadImage(imgUrl)
    }
}

@BindingAdapter("imgUri")
fun bindImage(imgView: ImageView, imgUrl: Uri?) {
    if (imgUrl != null) {
        imgView.loadImage(imgUrl)
    }
}