package com.denath.kotlinnewsapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.denath.kotlinnewsapp.R
import com.denath.kotlinnewsapp.adapter.NewsAdapter
import com.denath.kotlinnewsapp.models.Results
import com.denath.kotlinnewsapp.retrofit.RequestAPI
import com.denath.kotlinnewsapp.retrofit.RetrofitClient
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : AppCompatActivity(), MainView {

    private lateinit var buttonClickSubject: PublishSubject<Boolean>
    private lateinit var jsonApi: RequestAPI
    private var init = true

    lateinit var newsInteractor: NewsInteractor
    lateinit var newsPresenter: NewsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        initRecyclerView()
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(RequestAPI::class.java)

        newsInteractor = NewsInteractor(jsonApi)
        newsPresenter = NewsPresenter(newsInteractor, this)


    }

    override fun onStart() {
        buttonClickSubject = PublishSubject.create<Boolean>()
        newsPresenter.bind()

        if (init) {
            fetchBtn.setOnClickListener {
                buttonClickSubject.onNext(true)
                Log.d("button", "btn click")
            }
            init = false
        }

        super.onStart()
    }

    override fun onStop() {
        newsPresenter.unbind()
        super.onStop()
    }

    override fun render(newsViewState: NewsViewState) {
        Log.d("MVIState", "New state rendered!")
        with(newsViewState) {
            showLoading(loading)
            showError(error)
            showNews(resultsList)
        }
    }

//    override fun buttonClick(): Observable<Any> {
//        return RxView.clicks(fetchBtn)
//                .doOnNext { Log.d("elo", "onNext") }
//    }

    override fun buttonClick(): Observable<Boolean> = buttonClickSubject.doOnNext {
        Log.d("cokolwiek", "inside")
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
