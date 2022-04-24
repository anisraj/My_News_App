package com.example.mynewsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.databinding.LayoutNewsListItemBinding
import javax.inject.Inject

class NewsHeadLineAdapter @Inject constructor() : RecyclerView.Adapter<NewsHeadLineAdapter.ViewHolder>() {

    private val callBack = object : DiffUtil.ItemCallback<ApiResponse.Article>() {
        override fun areItemsTheSame(
            oldItem: ApiResponse.Article,
            newItem: ApiResponse.Article
        ): Boolean {
            return oldItem.url == newItem.url
        }
        override fun areContentsTheSame(
            oldItem: ApiResponse.Article,
            newItem: ApiResponse.Article
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutNewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(private val binding: LayoutNewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ApiResponse.Article) {
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source?.name
            Glide.with(binding.ivNewsHeadLines.context)
                .load(article.urlToImage)
                .into(binding.ivNewsHeadLines)
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener: ((ApiResponse.Article) -> Unit)? = null

    fun setOnClickListener(listener: (ApiResponse.Article) -> Unit) {
        onItemClickListener = listener
    }
}