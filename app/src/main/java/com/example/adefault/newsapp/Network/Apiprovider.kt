package com.example.adefault.newsapp.Network

import com.example.adefault.newsapp.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object Apiprovider {


    val client = OkHttpClient.Builder()
        .connectTimeout(5,TimeUnit.MINUTES)
        .build()

    fun providerApi() =
        Retrofit.Builder().client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
}