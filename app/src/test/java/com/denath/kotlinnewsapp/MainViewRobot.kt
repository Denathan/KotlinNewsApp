package com.denath.kotlinnewsapp

import com.denath.kotlinnewsapp.activity.MainView
import com.denath.kotlinnewsapp.activity.NewsPresenter
import com.denath.kotlinnewsapp.activity.NewsViewState
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import junit.framework.Assert

class MainViewRobot(newsPresenter: NewsPresenter) {

    private val buttonSubject = PublishSubject.create<Any>()
    val listOfStates = arrayListOf<NewsViewState>()

    private val mainView: MainView = object : MainView {
        override fun render(newsViewState: NewsViewState) {
            listOfStates.add(newsViewState)
        }

        override fun buttonIntent(): Observable<Any> {
            return buttonSubject
        }
    }

    init {
        newsPresenter.attachView(mainView)
    }

    fun performButtonClick() {
        buttonSubject.onNext(true)
    }

    fun assertViewStates(expectedViewStates: List<NewsViewState>) {
        Assert.assertEquals(listOfStates, expectedViewStates)
    }
}