package com.example.jet2android.data

import com.google.gson.annotations.SerializedName

data class GetBlogsResponse(
    @field:SerializedName("results") val results: List<Blog>
)