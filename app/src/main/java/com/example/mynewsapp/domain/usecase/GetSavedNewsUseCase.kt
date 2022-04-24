package com.example.mynewsapp.domain.usecase

import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    fun execute(): Flow<List<ApiResponse.Article>> {
        return newsRepository.getSavedNews()
    }
}