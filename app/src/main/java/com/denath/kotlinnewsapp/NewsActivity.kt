package com.denath.kotlinnewsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : AppCompatActivity() {

    val newsList: ArrayList<NewsDto> = ArrayList<NewsDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        foundNewsList.setHasFixedSize(true)
        foundNewsList.layoutManager = LinearLayoutManager(this)
        foundNewsList.adapter = NewsAdapter(newsList, this)
    }

    fun loadUrlData() {
        
    }
}
