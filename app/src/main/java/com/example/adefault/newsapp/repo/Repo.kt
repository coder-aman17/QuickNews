package com.example.adefault.newsapp.repo

import com.example.adefault.newsapp.Network.Apiprovider
import com.example.adefault.newsapp.Network.NewsModel

class Repo (){
    suspend fun newsProvider () : NewsModel? {
        return Apiprovider.providerApi().getAllNewsFromServer().body()
    }

}