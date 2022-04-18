package com.example.mynewsapp.data.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ApiResponse(
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null,
    @SerializedName("articles")
    @Expose
    var articles: List<Article?>? = null
) {
    data class Article(
        @SerializedName("source")
        @Expose
        var source: Source? = null,
        @SerializedName("author")
        @Expose
        var author: Any? = null,
        @SerializedName("title")
        @Expose
        var title: String? = null,
        @SerializedName("description")
        @Expose
        var description: String? = null,
        @SerializedName("url")
        @Expose
        var url: String? = null,
        @SerializedName("urlToImage")
        @Expose
        var urlToImage: String? = null,
        @SerializedName("publishedAt")
        @Expose
        var publishedAt: String? = null,
        @SerializedName("content")
        @Expose
        var content: String? = null
    ) {
        data class Source(
            @SerializedName("id")
            @Expose
            var id: String? = null,
            @SerializedName("name")
            @Expose
            var name: String? = null
        )
    }
}