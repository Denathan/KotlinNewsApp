package com.denath.kotlinnewsapp.activity

import com.denath.kotlinnewsapp.models.Results
import com.denath.kotlinnewsapp.retrofit.RequestAPI
import io.reactivex.Observable

class NewsInteractorImpl(private val jsonAPI: RequestAPI) : NewsInteractor {

    override fun getNews(): Observable<PartialNewsViewState> {
        return fetchNews()
                .map { PartialNewsViewState.NewsListFetchedState(it) as PartialNewsViewState }
                .onErrorReturn { PartialNewsViewState.ErrorState(it) }
    }

    private fun fetchNews(): Observable<List<Results>> {
        return jsonAPI.response
                .map { response -> response.response.results }
    }
}