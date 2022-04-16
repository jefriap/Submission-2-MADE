package com.jefriap.submission2made.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

//implementasi glide dengan ktx
fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}
fun ImageView.imageLoad(url: Int) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}