package com.denath.kotlinnewsapp.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.denath.kotlinnewsapp.R
import com.denath.kotlinnewsapp.R.id.fetchBtn
import com.denath.kotlinnewsapp.R.id.foundNewsList
import com.denath.kotlinnewsapp.adapter.NewsAdapter
import com.denath.kotlinnewsapp.models.Results
import com.denath.kotlinnewsapp.retrofit.RequestAPI
import com.denath.kotlinnewsapp.retrofit.RetrofitClient
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : MviActivity<MainView, NewsPresenter>(), MainView {
    override fun createPresenter(): NewsPresenter {
        return NewsPresenter(newsInteractorImpl)
    }

    private lateinit var jsonApi: RequestAPI

    lateinit var newsInteractorImpl: NewsInteractorImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        initRecyclerView()
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(RequestAPI::class.java)

        newsInteractorImpl = NewsInteractorImpl(jsonApi)
    }

    override fun render(newsViewState: NewsViewState) {
        Log.d("MVIState", "New state rendered!")
        with(newsViewState) {
            showLoading(loading)
            showError(error)
            showNews(resultsList)
        }
    }

    override fun buttonIntent(): Observable<Any> {
        return RxView.clicks(fetchBtn).doOnNext { Log.d("xd", "onNext") }
    }

    private fun initRecyclerView() {
        foundNewsList.setHasFixedSize(true)
        foundNewsList.layoutManager = LinearLayoutManager(this)
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            empty_list.visibility = View.VISIBLE
        } else {
            empty_list.visibility = View.GONE
        }
    }

    private fun showError(show: Throwable?) {
        show?.let { Log.e("Tag", "Error occured", show) }
    }

    private fun showNews(newsList: List<Results>) {
        val adapter = NewsAdapter(newsList, this)
        foundNewsList.adapter = adapter
    }
}
