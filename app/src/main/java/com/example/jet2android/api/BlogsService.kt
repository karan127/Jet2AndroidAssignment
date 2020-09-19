package com.example.jet2android.api

import com.example.jet2android.data.Blog
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BlogsService {

    @GET("jet2/api/v1/blogs")
    suspend fun getBlogs(
        @Query("page") page: Int,
        @Query("limit") perPage: Int,
    ): List<Blog>

    companion object {
        private const val BASE_URL = "https://5e99a9b1bc561b0016af3540.mockapi.io/"

        fun create(): BlogsService {

            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
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