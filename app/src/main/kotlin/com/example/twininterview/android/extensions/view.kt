package com.example.twininterview.android.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

interface IToggleView {
    fun clicked(boolean: Boolean)
}

inline fun toggleView(crossinline body: (Boolean) -> Unit) = object : IToggleView {
    override fun clicked(boolean: Boolean) {
        body(boolean)
    }
}

fun ImageView.loadFromUrl(url: String, requestOptions: RequestOptions) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .apply(requestOptions)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)