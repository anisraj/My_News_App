package com.example.mynewsapp.data.db

import androidx.room.TypeConverter
import com.example.mynewsapp.data.model.ApiResponse

class Converters {

    @TypeConverter
    fun fromSource(source: ApiResponse.Article.Source): String? {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): ApiResponse.Article.Source {
        return ApiResponse.Article.Source(null, name)
    }
}