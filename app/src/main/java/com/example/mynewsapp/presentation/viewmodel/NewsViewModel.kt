package com.example.mynewsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.utils.Resource
import com.example.mynewsapp.data.utils.Utility
import com.example.mynewsapp.domain.usecase.GetNewsHeadLinesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadLinesUseCase: GetNewsHeadLinesUseCase
) : AndroidViewModel(app) {

    private val newsHeadLines = MutableLiveData<Resource<ApiResponse>>()

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
}