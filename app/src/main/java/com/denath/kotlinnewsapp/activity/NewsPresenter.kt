package com.denath.kotlinnewsapp.activity

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class NewsPresenter(private val newsInteractor: NewsInteractor, private val mainView: MainView) {

    private val compositeDisposable = CompositeDisposable()
    private val behaviourSubject = BehaviorSubject.create<NewsViewState>()

    @SuppressLint("CheckResult")
    fun bind() {
        val buttonObservable = mainView.buttonClick()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    newsInteractor.getNews().subscribeOn(Schedulers.io())
                }
                .startWith(PartialNewsViewState.LoadingState)
                .doOnNext { Log.d("bind", "We are inside") }

        compositeDisposable.add(buttonObservable
                .scan(NewsViewState(), this::reduce)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(behaviourSubject)
                .subscribe({ mainView.render(it) },
                        { error -> Log.e("Subscription error: ", "$error") }))
    }

    fun unbind() {
        compositeDisposable.clear()
    }

    private fun reduce(previousState: NewsViewState, partialState: PartialNewsViewState): NewsViewState {
        return partialState.reduce(previousState)
    }
}