package com.example.mynewsapp.data.repository.dataSource

import com.example.mynewsapp.data.model.ApiResponse
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticleToDb(article: ApiResponse.Article)

    /**
     * fun for getting saved news
     * @return Flow<List<ApiResponse.Article>>
     * */
    fun getSavedNews(): Flow<List<ApiResponse.Article>>
}