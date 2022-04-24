package com.example.mynewsapp.presentation.di

import android.app.Application
import com.example.mynewsapp.domain.usecase.GetNewsHeadLinesUseCase
import com.example.mynewsapp.domain.usecase.SaveNewsUseCase
import com.example.mynewsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadLinesUseCase: GetNewsHeadLinesUseCase,
        saveNewsUseCase: SaveNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(application, getNewsHeadLinesUseCase, saveNewsUseCase)
    }
}