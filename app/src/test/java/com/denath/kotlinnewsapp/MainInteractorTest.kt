package com.denath.kotlinnewsapp

import com.denath.kotlinnewsapp.activity.NewsInteractorImpl
import com.denath.kotlinnewsapp.activity.PartialNewsViewState
import com.denath.kotlinnewsapp.models.News
import com.denath.kotlinnewsapp.models.Response
import com.denath.kotlinnewsapp.retrofit.RequestAPI
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import org.junit.Test

class MainInteractorTest {

    @Test
    fun testSuccessfulResultFetching() {

        val news = News(status = "xd")

        val response = Response(news)

        val jsonAPI: RequestAPI = mock {
            on { it.response } doReturn Observable.just(response)
        }

        val interactor = NewsInteractorImpl(jsonAPI)

        interactor.getNews()
                .test()
                .assertValue(PartialNewsViewState.NewsListFetchedState(response.response.results))
    }
}