package com.example.mynewsapp.data.repository.dataSource

import com.example.mynewsapp.data.model.ApiResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopNewsHeadLines(country: String, page: Int): Response<ApiResponse>
}