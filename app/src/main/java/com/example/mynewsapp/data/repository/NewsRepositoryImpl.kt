package com.example.mynewsapp.data.repository

import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.repository.dataSource.NewsLocalDataSource
import com.example.mynewsapp.data.repository.dataSource.NewsRemoteDataSource
import com.example.mynewsapp.data.utils.Resource
import com.example.mynewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
): NewsRepository {
    override suspend fun getNewsHeadLines(country: String, page: Int): Resource<ApiResponse> {
        return responseToResource(newsRemoteDataSource.getTopNewsHeadLines(country, page))
    }

    private fun responseToResource(response: Response<ApiResponse>): Resource<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun saveNews(article: ApiResponse.Article) {
        newsLocalDataSource.saveArticleToDb(article)
    }

    override suspend fun deleteNews(article: ApiResponse.Article) {
        return newsLocalDataSource.deleteArticleFromDb(article)
    }

    override fun getSavedNews(): Flow<List<ApiResponse.Article>> {
        return newsLocalDataSource.getSavedNews()
    }
}