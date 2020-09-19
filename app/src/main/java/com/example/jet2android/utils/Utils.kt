package com.example.jet2android.utils

import com.example.jet2android.api.BlogsService
import com.example.jet2android.data.BlogRepository
import com.example.jet2android.viewmodels.BlogsViewModelFactory

object Utils {
    fun provideBlogsViewModelFactory(): BlogsViewModelFactory {
        val repository = BlogRepository(BlogsService.create())
        return BlogsViewModelFactory(repository)
    }
}