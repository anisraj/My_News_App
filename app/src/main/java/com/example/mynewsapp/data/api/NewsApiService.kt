package com.example.mynewsapp.data.api

import com.example.mynewsapp.BuildConfig
import com.example.mynewsapp.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/top-headlines")
    suspend fun getNewsHeadLines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("apiKey") api_key: String = BuildConfig.API_KEY
    ): Response<ApiResponse>
}