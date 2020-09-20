package com.example.jet2android.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jet2android.R
import com.example.jet2android.data.Blog
import com.example.jet2android.databinding.ListItemBlogBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
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
    view.text = view.context.getString(R.string.str_likes, getFormattedNumber(likes.toLong()))
}

@BindingAdapter("setComments")
fun setComments(view: TextView, comments: Int) {
    view.text = view.context.getString(R.string.str_comments, getFormattedNumber(comments.toLong()))
}

@BindingAdapter("setBlogTime")
fun setBlogTime(view: TextView, createdAt: String) {
    view.text = getTimeDifference(createdAt)
}

@BindingAdapter("circularImageFromUrl")
fun circularImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .circleCrop()
            .into(view)
    }
}

@BindingAdapter("imageFromUrl")
fun imageFromUrl(view: ImageView, imageUrl: String?) {
    System.out.println("Image URL - " + imageUrl)
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .centerCrop()
            .into(view)
    }
}

@SuppressLint("SimpleDateFormat")
private fun getTimeDifference(createdAt: String): CharSequence? {
    var day = 0
    var hh = 0
    var mm = 0
    try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val oldDate: Date = dateFormat.parse(createdAt)
        val cDate = Date()
        val timeDiff: Long = cDate.time - oldDate.time
        day = TimeUnit.MILLISECONDS.toDays(timeDiff).toInt()
        hh =
            ((TimeUnit.MILLISECONDS.toHours(timeDiff) - TimeUnit.DAYS.toHours(day.toLong())).toInt())
        mm = ((TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(timeDiff)
        )).toInt())
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return if (day != 0) {
        "$day d"
    } else if (hh != 0) {
        "$hh hr"
    } else {
        "$mm min"
    }
}


@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

private fun getFormattedNumber(count: Long): String {
    if (count < 1000) return "" + count
    val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
    return String.format("%.1f%c", count / 1000.0.pow(exp.toDouble()), "KMGTPE"[exp - 1])
}