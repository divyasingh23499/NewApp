package com.example.newsly

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// http://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=bd1
const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "bd1232159ecd4081af2f2dd466c67944"

interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLines (@Query("country")country: String, @Query("page")page: Int):  retrofit2.Call<News>

    /* https://newsapi.org/v2/top-headlines?apiKey=$API_KEY This API will be hit by get */
}

// we will create singleton object of interface
object NewsService {
    val newsInstance: NewsInterface
    init {

        // This is object of retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Here We are Asking for implementation of interface
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}