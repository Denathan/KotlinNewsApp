package com.denath.kotlinnewsapp.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.denath.kotlinnewsapp.Adapter.NewsAdapter
import com.denath.kotlinnewsapp.Data.Results
import com.denath.kotlinnewsapp.R
import com.denath.kotlinnewsapp.Retrofit.RequestAPI
import com.denath.kotlinnewsapp.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : AppCompatActivity(), MainView {

    override fun showNews(newsList: List<Results>) {
        val adapter = NewsAdapter(newsList, this)
        foundNewsList.adapter = adapter
    }

    private lateinit var jsonApi: RequestAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        initRecyclerView()
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(RequestAPI::class.java)

        val newsInteractor = NewsInteractor(jsonApi)
        val newsPresenter = NewsPresenter(newsInteractor, this)
        newsPresenter.getNews()
    }

    private fun initRecyclerView() {
        foundNewsList.setHasFixedSize(true)
        foundNewsList.layoutManager = LinearLayoutManager(this)
    }
}
