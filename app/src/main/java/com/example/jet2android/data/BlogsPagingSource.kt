package com.example.jet2android.data

import androidx.paging.PagingSource
import com.example.jet2android.api.BlogsService

private const val STARTING_PAGE_INDEX = 1

class BlogsPagingSource(
    private val service: BlogsService,
    private val limit: Int
) : PagingSource<Int, Blog>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Blog> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val blogs = service.getBlogs(page, limit)
            LoadResult.Page(
                data = blogs,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (blogs.size < limit) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}