package com.example.mynewsapp.domain.usecase

import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.utils.Resource
import com.example.mynewsapp.domain.repository.NewsRepository

class GetNewsHeadLinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(): Resource<ApiResponse> {
        return newsRepository.getNewsHeadLines()
    }
}