package com.denath.kotlinnewsapp

import com.denath.kotlinnewsapp.activity.NewsInteractor
import com.denath.kotlinnewsapp.activity.NewsPresenter
import com.denath.kotlinnewsapp.activity.NewsViewState
import com.denath.kotlinnewsapp.activity.PartialNewsViewState
import com.denath.kotlinnewsapp.models.Results
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test


class MainPresenterTest {

    init {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun WhenUserClicksButtonThenShowProperState() {
        val testResults = listOf(Results())
        val interactor: NewsInteractor = mock {
            on { it.getNews() } doReturn Observable.just(PartialNewsViewState.NewsListFetchedState(testResults))
                    .cast(PartialNewsViewState::class.java)
        }

        val presenter = NewsPresenter(interactor)

        val viewRobot = MainViewRobot(presenter)

        viewRobot.performButtonClick()

        viewRobot.assertViewStates(listOf(NewsViewState(), NewsViewState(false, null, testResults)))
    }
}