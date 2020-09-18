package com.example.jet2android.data

import com.google.gson.annotations.SerializedName

data class Blog(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("createdAt") val createdAt: String,
    @field:SerializedName("content") val content: String,
    @field:SerializedName("comments") val comments: Int,
    @field:SerializedName("likes") val likes: Int,
    @field:SerializedName("media") val media: List<Media>,
    @field:SerializedName("user") val user: List<User>
)