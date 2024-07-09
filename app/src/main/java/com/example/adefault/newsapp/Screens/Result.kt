package com.example.adefault.newsapp.Screens



sealed class Result <T>(
    val data:T? = null,
    val message:String? = null
){
    class Success <T> (data: T?) : Result<T>( data = data)
    class Loading <T> (message: String?) : Result<T>()
    class Error <T> (message: String?) : Result<T>(message = message)
}
