package com.example.mynewsapp.data.repository

import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.repository.dataSource.NewsRemoteDataSource
import com.example.mynewsapp.data.utils.Resource
import com.example.mynewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
): NewsRepository {
    override suspend fun getNewsHeadLines(): Resource<ApiResponse> {
        return responseToResource(newsRemoteDataSource.getTopNewsHeadLines())
    }

    private fun responseToResource(response: Response<ApiResponse>): Resource<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<ApiResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: ApiResponse.Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: ApiResponse.Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<ApiResponse.Article>> {
        TODO("Not yet implemented")
    }
}