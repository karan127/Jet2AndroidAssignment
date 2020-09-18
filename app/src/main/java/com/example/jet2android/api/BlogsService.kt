package com.example.jet2android.api

import com.example.jet2android.data.GetBlogsResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BlogsService {

    @GET("jet2/api/v1/blogs")
    suspend fun getBlogs(
        @Query("page") page: Int,
        @Query("limit") perPage: Int,
    ): GetBlogsResponse

    companion object {
        private const val BASE_URL = "https://5e99a9b1bc561b0016af3540.mockapi.io/s"

        fun create(): BlogsService {

            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BlogsService::class.java)
        }
    }
}