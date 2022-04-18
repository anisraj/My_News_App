package com.example.mynewsapp.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiServiceTest {
    private lateinit var service: NewsApiService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    private fun enqueMockResponse(filename: String) {
        val inputStream = javaClass.classLoader.getResourceAsStream(filename)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getTopHeadLines_sentRequest_receivedExpected() {
        runBlocking {
            enqueMockResponse("newsresponse.json")
            val responseBody = service.getNewsHeadLines("in", 1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path.equals("/v2/top-headlines?country=in&page=1&apiKey=06f43647af014da18e4b5b3253af8eb1"))
        }
    }

    @Test
    fun getTopHeadLines_receivedResponse_correctPageSize() {
        runBlocking {
            enqueMockResponse("newsresponse.json")
            val responseBody = service.getNewsHeadLines("in", 1).body()
            val articleList = responseBody?.articles
            assertThat(articleList?.size).isEqualTo(20)
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}