package com.denath.kotlinnewsapp.activity

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsPresenter(private val newsInteractor: NewsInteractor) : MviBasePresenter<MainView, NewsViewState>() {

    override fun bindIntents() {

        val click: Observable<NewsViewState> =
                intent { it.buttonIntent() }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .flatMap { newsInteractor.getNews().subscribeOn(Schedulers.io()) }
                        .startWith(PartialNewsViewState.LoadingState)
                        .scan(NewsViewState(), this::reduce)
                        .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(click, MainView::render)
    }

    private fun reduce(previousState: NewsViewState, partialState: PartialNewsViewState): NewsViewState {
        return partialState.reduce(previousState)
    }
}