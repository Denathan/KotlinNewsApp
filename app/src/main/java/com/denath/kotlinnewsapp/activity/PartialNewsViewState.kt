package com.denath.kotlinnewsapp.activity

import com.denath.kotlinnewsapp.models.Results

sealed class PartialNewsViewState {

    abstract fun reduce(previousState: NewsViewState): NewsViewState

    object LoadingState : PartialNewsViewState() {
        override fun reduce(previousState: NewsViewState): NewsViewState = NewsViewState(loading = true)
    }

    class ErrorState(private val throwable: Throwable) : PartialNewsViewState() {
        override fun reduce(previousState: NewsViewState): NewsViewState = NewsViewState(error = throwable)
    }

    class NewsListFetchedState(private val resultsList: List<Results>) : PartialNewsViewState() {
        override fun reduce(previousState: NewsViewState): NewsViewState = NewsViewState(resultsList = resultsList, loading = false)
    }
}