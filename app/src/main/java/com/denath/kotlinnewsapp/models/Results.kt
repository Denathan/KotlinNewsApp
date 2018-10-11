package com.denath.kotlinnewsapp.models

data class Results(
        val id: String = "",
        val type: String = "",
        val sectionId: String = "",
        val sectionName: String = "",
        val webPublicationDate: String = "",
        val webTitle: String = "",
        val webUrl: String = "",
        val apiUrl: String = "",
        val isHosted: Boolean = false,
        val pillarId: String = "",
        val pillarName: String = ""
)