package com.example.jet2android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.jet2android.adapters.BlogAdapter
import com.example.jet2android.databinding.BlogsFragmentBinding
import com.example.jet2android.utils.Utils
import com.example.jet2android.viewmodels.BlogsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BlogsFragment : Fragment() {

    private lateinit var binding: BlogsFragmentBinding
    private val adapter = BlogAdapter()
    private var networkJob: Job? = null

    private val viewModel: BlogsViewModel by viewModels {
        Utils.provideBlogsViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BlogsFragmentBinding.inflate(inflater, container, false)
        binding.blogList.adapter = adapter

        getBlogs()

        return binding.root
    }

    @ExperimentalCoroutinesApi
    private fun getBlogs() {
        // Make sure we cancel the previous job before creating a new one
        networkJob?.cancel()
        networkJob = lifecycleScope.launch {
            viewModel.getBlogs().collectLatest {
                adapter.submitData(it)
            }
        }
    }

}