package com.denath.kotlinnewsapp.activity

import io.reactivex.Observable

interface NewsInteractor {
    fun getNews(): Observable<PartialNewsViewState>
}