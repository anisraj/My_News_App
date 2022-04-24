package com.example.mynewsapp.data.repository.dataSourceImpl

import com.example.mynewsapp.data.db.ArticleDao
import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao
) : NewsLocalDataSource {
    override suspend fun saveArticleToDb(article: ApiResponse.Article) {
        articleDao.insert(article)
    }

    override fun getSavedNews(): Flow<List<ApiResponse.Article>> {
        return articleDao.getAllArticles()
    }
}