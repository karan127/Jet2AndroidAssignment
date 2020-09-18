package com.example.jet2android.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jet2android.api.BlogsService
import kotlinx.coroutines.flow.Flow

class BlogRepository(private val service: BlogsService) {
    fun getBlogs(limit: Int): Flow<PagingData<Blog>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { BlogsPagingSource(service, limit) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}