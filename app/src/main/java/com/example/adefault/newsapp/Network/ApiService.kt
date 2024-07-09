package com.example.adefault.newsapp.Network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{





    @GET("top-headlines")
    suspend fun getAllNewsFromServer(
        @Query("sources") sources : String="techcrunch",
        @Query("apiKey") apiKey : String="8a605a9466e64d24a1e5b07978de4203"
    ): Response<NewsModel>
}