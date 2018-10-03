package com.denath.kotlinnewsapp.Activity

import android.support.annotation.MainThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsPresenter(private val newsInteractor: NewsInteractor, private val mainView: MainView) {

    fun getNews() {
        newsInteractor.fetchNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { results -> mainView.showNews(results) } )
    }
}