package com.example.jet2android.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jet2android.data.BlogRepository

@Suppress("UNCHECKED_CAST")
class BlogsViewModelFactory(
    private val blogsRepository: BlogRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BlogsViewModel(
            blogsRepository
        ) as T
    }
}