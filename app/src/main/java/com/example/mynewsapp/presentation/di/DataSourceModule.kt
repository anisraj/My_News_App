package com.example.mynewsapp.presentation.di

import com.example.mynewsapp.data.api.NewsApiService
import com.example.mynewsapp.data.db.ArticleDao
import com.example.mynewsapp.data.repository.dataSource.NewsLocalDataSource
import com.example.mynewsapp.data.repository.dataSource.NewsRemoteDataSource
import com.example.mynewsapp.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import com.example.mynewsapp.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsApiService)
    }

    @Singleton
    @Provides
    fun provideNewsLocalDataSource(articleDao: ArticleDao): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(articleDao)
    }
}