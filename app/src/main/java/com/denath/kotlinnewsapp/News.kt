package com.denath.kotlinnewsapp

data class News (
        val id: String,
        val type: String,
        val sectionId: String,
        val sectionName: String,
        val webPublicationDate: String,
        val webTitle: String,
        val webUrl: String,
        val apiUrl: String,
        val isHosted: Boolean,
        val pillarId: String,
        val pillarName: String
)