package com.example.adefault.newsapp.Screens

import android.net.Network
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adefault.newsapp.Network.Article
import com.example.adefault.newsapp.Network.NewsModel
import com.example.adefault.newsapp.repo.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class NewsViewModel : ViewModel() {
    private val _newsArticles = MutableStateFlow<List<Article>>(emptyList())
    val newsArticles: StateFlow<List<Article>> = _newsArticles

    init {
        viewModelScope.launch {
            val news = getNews(Repo())
            _newsArticles.value = news?.articles ?: emptyList()
        }
    }

    private suspend fun getNews(repo: Repo): NewsModel? {
        return repo.newsProvider()
    }
}
