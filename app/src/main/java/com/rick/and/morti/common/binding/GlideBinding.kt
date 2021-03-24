package com.rick.and.morti.common.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("poster")
fun ImageView.setPoster(img: String) {
    Glide.with(context)
        .load(img)
        .into(this)
}