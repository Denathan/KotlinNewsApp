package com.denath.kotlinnewsapp.activity

import com.denath.kotlinnewsapp.models.Results

data class NewsViewState(val loading: Boolean = true,
                         val error: Throwable? = null,
                         val resultsList: List<Results> = listOf())