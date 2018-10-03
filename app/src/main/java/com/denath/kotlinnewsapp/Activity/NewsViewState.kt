package com.denath.kotlinnewsapp.Activity

import com.denath.kotlinnewsapp.Data.Response

data class NewsViewState(val error: Boolean = false,
                         val response: Response)