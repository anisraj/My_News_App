package com.example.mynewsapp.presentation.di

import com.example.mynewsapp.data.repository.NewsRepositoryImpl
import com.example.mynewsapp.data.repository.dataSource.NewsLocalDataSource
import com.example.mynewsapp.data.repository.dataSource.NewsRemoteDataSource
import com.example.mynewsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)
    }
}