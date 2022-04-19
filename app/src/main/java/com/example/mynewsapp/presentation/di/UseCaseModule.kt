package com.example.mynewsapp.presentation.di

import com.example.mynewsapp.domain.repository.NewsRepository
import com.example.mynewsapp.domain.usecase.GetNewsHeadLinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsHeadLinesUseCase(newsRepository: NewsRepository): GetNewsHeadLinesUseCase {
        return GetNewsHeadLinesUseCase(newsRepository)
    }
}