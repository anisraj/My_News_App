package com.example.mynewsapp.data.repository.dataSourceImpl

import com.example.mynewsapp.data.api.NewsApiService
import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService,
    private val country: String,
    private val page: Int
): NewsRemoteDataSource {

    override suspend fun getTopNewsHeadLines(): Response<ApiResponse> {
        return newsApiService.getNewsHeadLines(country, page)
    }
}