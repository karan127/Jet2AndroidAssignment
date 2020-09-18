package com.example.jet2android.data

import com.google.gson.annotations.SerializedName

data class Media(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("blogId") val blogId: String,
    @field:SerializedName("createdAt") val createdAt: String,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("url") val url: String
)