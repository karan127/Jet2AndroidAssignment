package com.example.jet2android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jet2android.data.Blog
import com.example.jet2android.databinding.ListItemBlogBinding
import kotlin.math.ln
import kotlin.math.pow

class BlogAdapter :
    PagingDataAdapter<Blog, BlogAdapter.BlogViewHolder>(BlogDifferenciateCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(
            ListItemBlogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blog = getItem(position)

        if (blog != null) {
            holder.bind(blog)
        }
    }

    class BlogViewHolder(
        private val binding: ListItemBlogBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(item: Blog) {
            binding.apply {
                blog = item
                executePendingBindings()
            }
        }
    }
}

private class BlogDifferenciateCallback : DiffUtil.ItemCallback<Blog>() {
    override fun areItemsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem == newItem
    }
}

@BindingAdapter("setLikes")
fun setLikes(view: TextView, likes: Int) {
    view.text = getFormattedNumber(likes.toLong()) + " Likes"
}

@BindingAdapter("setComments")
fun setComments(view: TextView, likes: Int) {
    view.text = getFormattedNumber(likes.toLong()) + " Comments"
}

private fun getFormattedNumber(count: Long): String {
    if (count < 1000) return "" + count
    val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
    return String.format("%.1f%c", count / 1000.0.pow(exp.toDouble()), "KMGTPE"[exp - 1])
}