package com.example.jet2android.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jet2android.data.Blog
import com.example.jet2android.data.BlogRepository
import kotlinx.coroutines.flow.Flow

class BlogsViewModel internal constructor(
    private val repository: BlogRepository
) : ViewModel() {
    private var currentBlogs: Flow<PagingData<Blog>>? = null
    private val limit = 10

    fun getBlogs(): Flow<PagingData<Blog>> {
        val newResult: Flow<PagingData<Blog>> =
            repository.getBlogs(limit).cachedIn(viewModelScope)
        currentBlogs = newResult
        System.out.println("*****getBlogs*******" + newResult)
        return newResult
    }
}