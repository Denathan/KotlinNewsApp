package com.denath.kotlinnewsapp.Activity

import com.denath.kotlinnewsapp.Data.Results
import okhttp3.Response

interface MainView {

    fun showNews(newsList: List<Results>)
}