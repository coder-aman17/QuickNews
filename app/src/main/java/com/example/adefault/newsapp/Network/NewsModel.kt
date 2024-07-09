package com.example.adefault.newsapp.Network


data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)