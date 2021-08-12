package com.example.twininterview.android.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.example.twininterview.android.R

@BindingAdapter("twin_app:loadImage")
fun loadImage(imageView: ImageView, url: String?) {
    url?.let {
        RequestOptions().run {
            placeholder(R.drawable.ic_add_meal)
            imageView.loadFromUrl(url, this)
        }
    }
}