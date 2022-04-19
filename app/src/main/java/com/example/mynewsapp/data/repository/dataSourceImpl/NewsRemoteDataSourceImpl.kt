package com.example.mynewsapp.data.repository.dataSourceImpl

import com.example.mynewsapp.data.api.NewsApiService
import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
): NewsRemoteDataSource {

    override suspend fun getTopNewsHeadLines(country: String, page: Int): Response<ApiResponse> {
        return newsApiService.getNewsHeadLines(country, page)
    }
}