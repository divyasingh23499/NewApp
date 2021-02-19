package com.example.newsly

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()
    var pageNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // binding recyclers view
        adapter = NewsAdapter(this@MainActivity, articles)
        newList.adapter = adapter
        newList.layoutManager = LinearLayoutManager(this@MainActivity)

        getNews()
        pageNumber++
        getNews()

    }

    private fun getNews() {

        // we are calling news service file
        val news: Call<News> = NewsService.newsInstance.getHeadLines("in", pageNumber)
        news.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Show", "Error in fetching News", t)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val newNews: News? = response.body()
                if (newNews != null) {
                    //var totalResults = newNews.totalResults
                    Log.d("TAG", newNews.toString());
                    articles.addAll(newNews.articles)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

}