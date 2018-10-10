package com.denath.kotlinnewsapp.models

data class News(
        val status: String,
        val userTier: String,
        val total: Int,
        val startIndex: Int,
        val pageSize: Int,
        val currentPage: Int,
        val pages: Int,
        val orderBy: String,
        val results: List<Results>
)