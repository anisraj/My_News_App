package com.example.mynewsapp.domain.usecase

import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.utils.Resource
import com.example.mynewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsHeadLinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, page: Int): Resource<ApiResponse> {
        return newsRepository.getNewsHeadLines(country, page)
    }
}