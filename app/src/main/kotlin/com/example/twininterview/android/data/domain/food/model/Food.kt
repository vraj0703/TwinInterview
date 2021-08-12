package com.example.twininterview.android.data.domain.food.model

import com.google.gson.annotations.SerializedName

data class Food (
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("is_recent")
    val isRecent: Boolean,

    @SerializedName("is_tfy")
    val isTfy: Boolean
)
