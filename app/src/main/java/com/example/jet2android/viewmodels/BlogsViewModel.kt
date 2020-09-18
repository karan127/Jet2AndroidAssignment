package com.example.jet2android.viewmodels

import androidx.lifecycle.ViewModel
import com.example.jet2android.data.BlogRepository

class BlogsViewModel internal constructor(
    private val repository: BlogRepository
) : ViewModel()