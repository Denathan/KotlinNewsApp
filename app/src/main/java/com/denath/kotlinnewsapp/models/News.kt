package com.denath.kotlinnewsapp.models

data class News(
        val status: String = "",
        val userTier: String = "",
        val total: Int = 0,
        val startIndex: Int = 0,
        val pageSize: Int = 0,
        val currentPage: Int = 0,
        val pages: Int = 0,
        val orderBy: String = "",
        val results: List<Results> = listOf()
)