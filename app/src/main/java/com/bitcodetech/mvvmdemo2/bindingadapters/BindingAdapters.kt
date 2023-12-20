package com.bitcodetech.mvvmdemo2.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bitcodetech.mvvmdemo2.R
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun loadImageUrlToImageView(imageView: ImageView, value : String) {
    Glide.with(imageView)
        .load(value)
        .circleCrop()
        .error(R.mipmap.ic_launcher)
        .placeholder(R.mipmap.ic_launcher)
        .into(imageView)
}