package com.example.mynewsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.utils.Resource
import com.example.mynewsapp.data.utils.Utility
import com.example.mynewsapp.domain.usecase.GetNewsHeadLinesUseCase
import com.example.mynewsapp.domain.usecase.GetSavedNewsUseCase
import com.example.mynewsapp.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadLinesUseCase: GetNewsHeadLinesUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase
) : AndroidViewModel(app) {

    private val newsHeadLines = MutableLiveData<Resource<ApiResponse>>()
    val routeNewsHeadLines: LiveData<Resource<ApiResponse>> = newsHeadLines

    fun getNewsHeadlines(country: String, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                newsHeadLines.postValue(Resource.Loading())
                if (Utility.isNetworkAvailable(app)) {
                    val apiResult = getNewsHeadLinesUseCase.execute(country, page)
                    newsHeadLines.postValue(apiResult)
                } else {
                    newsHeadLines.postValue(Resource.Error("Internet is not available"))
                }
            } catch (ex: Exception) {
                newsHeadLines.postValue(Resource.Error(ex.message?:"Error"))
            }
        }
    }

    /**
     *  fun for saving article into room database
     *  @param article article to save
     * */
    fun saveArticle(article: ApiResponse.Article) {
        viewModelScope.launch {
            saveNewsUseCase.execute(article)
        }
    }

    /**
     * fun for getting saved news
     * @return LiveData<List<ApiResponse.Article>>
     * */
    fun getSavedNews(): LiveData<List<ApiResponse.Article>> {
        return liveData {
            getSavedNewsUseCase.execute().collect {
                emit(it)
            }
        }
    }
}