package com.example.mynewsapp.domain.usecase

import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class SaveNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute(article: ApiResponse.Article) {
        newsRepository.saveNews(article)
    }
}