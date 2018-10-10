package com.denath.kotlinnewsapp.activity

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface MainView: MvpView {

    fun render(newsViewState: NewsViewState)

//    fun buttonClick(): Observable<Any>

    fun buttonClick(): Observable<Boolean>
    fun buttonIntent(): Observable<Any>
}