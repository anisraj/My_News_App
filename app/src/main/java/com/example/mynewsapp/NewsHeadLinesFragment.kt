package com.example.mynewsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapp.data.utils.Resource
import com.example.mynewsapp.databinding.FragmentNewsHeadLinesBinding
import com.example.mynewsapp.presentation.adapter.NewsHeadLineAdapter
import com.example.mynewsapp.presentation.viewmodel.NewsViewModel

class NewsHeadLinesFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsHeadLinesBinding
    private lateinit var newsAdapter: NewsHeadLineAdapter
    private var country = "IN"
    private var page = 1

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
        initRecyclerview()
        viewNewsList()
    }

    private fun initRecyclerview() {
        newsAdapter = NewsHeadLineAdapter()
        binding.rvNewsHeadLines.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
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
        binding.progressBar.visibility = if (value) View.VISIBLE else View.GONE
    }
}