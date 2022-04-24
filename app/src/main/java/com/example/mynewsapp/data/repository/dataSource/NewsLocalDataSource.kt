package com.example.mynewsapp.data.repository.dataSource

import com.example.mynewsapp.data.model.ApiResponse

interface NewsLocalDataSource {
    suspend fun saveArticleToDb(article: ApiResponse.Article)
}