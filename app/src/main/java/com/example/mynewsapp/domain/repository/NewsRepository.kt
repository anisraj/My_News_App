package com.example.mynewsapp.domain.repository

import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadLines(country: String, page: Int): Resource<ApiResponse>
    suspend fun getSearchedNews(searchQuery: String): Resource<ApiResponse>
    suspend fun saveNews(article: ApiResponse.Article)
    suspend fun deleteNews(article: ApiResponse.Article)
    fun getSavedNews(): Flow<List<ApiResponse.Article>>
}