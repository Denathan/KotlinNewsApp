package com.denath.kotlinnewsapp.MVP

import com.denath.kotlinnewsapp.Activity.NewsViewState
import com.denath.kotlinnewsapp.Data.Response


interface AppInterface {

    interface View {

        fun render(newsViewState: NewsViewState)

        fun displayData(response: Response)
    }

    interface Presenter {

        fun loadUrlData()
    }

    interface Model {

    }

}