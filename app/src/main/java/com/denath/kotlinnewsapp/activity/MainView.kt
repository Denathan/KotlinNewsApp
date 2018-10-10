package com.denath.kotlinnewsapp.activity

import io.reactivex.Observable

interface MainView {

    fun render(newsViewState: NewsViewState)

//    fun buttonClick(): Observable<Any>

    fun buttonClick(): Observable<Boolean>
}