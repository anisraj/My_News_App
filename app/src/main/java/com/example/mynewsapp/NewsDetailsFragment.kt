package com.example.mynewsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.utils.VariableConstants
import com.example.mynewsapp.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsDetailsBinding.bind(view)
        val article = arguments?.getSerializable(VariableConstants.SELECTED_ARTICLE) as ApiResponse.Article
        binding.webView.apply {
            webViewClient = WebViewClient()
            if (article.url != "") {
                article.url?.let { loadUrl(it) }
            }
        }
    }
}