package com.example.mynewsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mynewsapp.data.model.ApiResponse
import com.example.mynewsapp.data.utils.VariableConstants
import com.example.mynewsapp.data.utils.VariableConstants.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ApiResponse.Article)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllArticles(): Flow<List<ApiResponse.Article>>
}