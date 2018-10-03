package com.denath.kotlinnewsapp.Activity

import com.denath.kotlinnewsapp.Data.Results
import com.denath.kotlinnewsapp.Retrofit.RequestAPI
import io.reactivex.Observable

class NewsInteractor(private val jsonAPI: RequestAPI) {

    fun fetchNews(): Observable<List<Results>> {
        return jsonAPI.response
                .map { response -> response.response.results }
    }

}