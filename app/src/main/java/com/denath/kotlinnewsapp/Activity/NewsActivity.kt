package com.denath.kotlinnewsapp.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.denath.kotlinnewsapp.Adapter.NewsAdapter
import com.denath.kotlinnewsapp.Data.Response
import com.denath.kotlinnewsapp.R
import com.denath.kotlinnewsapp.Retrofit.RequestAPI
import com.denath.kotlinnewsapp.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : AppCompatActivity() {

    private lateinit var jsonApi: RequestAPI
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(RequestAPI::class.java)

        foundNewsList.setHasFixedSize(true)
        foundNewsList.layoutManager = LinearLayoutManager(this)
        loadUrlData()
    }

    private fun loadUrlData() {
        compositeDisposable.add(jsonApi.response
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{response -> displayData(response)})
    }

    private fun displayData(response: Response) {
        val adapter = NewsAdapter(response.response.results, this)
        foundNewsList.adapter = adapter
    }
}
