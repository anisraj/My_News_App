package com.example.mynewsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsapp.data.utils.Resource
import com.example.mynewsapp.data.utils.VariableConstants
import com.example.mynewsapp.databinding.FragmentNewsHeadLinesBinding
import com.example.mynewsapp.presentation.adapter.NewsHeadLineAdapter
import com.example.mynewsapp.presentation.viewmodel.NewsViewModel

class NewsHeadLinesFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsHeadLinesBinding
    private lateinit var newsAdapter: NewsHeadLineAdapter
    private var country = "IN"
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_head_lines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsHeadLinesBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable(VariableConstants.SELECTED_ARTICLE, it)
            }
            findNavController().navigate(
                R.id.action_newsHeadLinesFragment_to_newsDetailsFragment,
                bundle
            )
        }
        initRecyclerview()
        viewNewsList()
    }

    private fun initRecyclerview() {
        binding.rvNewsHeadLines.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsHeadLinesFragment.scrollListener)
        }
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadlines(country, page)
        viewModel.routeNewsHeadLines.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    changeProgress(false)
                    it.data?.let { response ->
                        newsAdapter.differ.submitList(response.articles?.toList())
                        if (response.totalResults?.rem(20) == 0) {
                            pages = response.totalResults!! / 20
                        } else {
                            pages = response.totalResults!! / 20 + 1
                        }
                        isLastPage = page == pages

                    }
                }
                is Resource.Error -> {
                    changeProgress(false)
                    it.message?.let { message ->
                        Toast.makeText(activity, "An error occurred $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    changeProgress(true)
                }
            }
        }
    }

    private fun changeProgress(value: Boolean) {
        isLoading = value
        binding.progressBar.visibility = if (value) View.VISIBLE else View.GONE
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNewsHeadLines.layoutManager as LinearLayoutManager
            val sizeOfList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedEnd = topPosition + visibleItems >= sizeOfList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedEnd && isScrolling
            if (shouldPaginate) {
                page++
                isScrolling = false
                viewModel.getNewsHeadlines(country, page)
            }
        }
    }
}