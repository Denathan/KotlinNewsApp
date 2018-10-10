package com.denath.kotlinnewsapp.activity

import android.util.Log
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsPresenter(private val newsInteractor: NewsInteractor, private val mainView: MainView) : MviBasePresenter<MainView, NewsViewState>() {

    override fun bindIntents() {

        val click: Observable<NewsViewState> =
                intent {
                    mainView.buttonIntent()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .flatMap {
                                newsInteractor.getNews().subscribeOn(Schedulers.io())
                            }
                            .startWith(PartialNewsViewState.LoadingState)
                            .doOnNext { Log.d("bind", "We are inside") }
                }
                        .scan(NewsViewState(), this::reduce)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(click, MainView::render)
    }

    private fun reduce(previousState: NewsViewState, partialState: PartialNewsViewState): NewsViewState {
        return partialState.reduce(previousState)
    }
}