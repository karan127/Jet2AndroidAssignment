package com.example.jet2android.data

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("blogId") val blogId: String,
    @field:SerializedName("createdAt") val createdAt: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("avatar") val avatar: String,
    @field:SerializedName("lastname") val lastname: String,
    @field:SerializedName("city") val city: String,
    @field:SerializedName("designation") val designation: String,
    @field:SerializedName("about") val about: String,
)