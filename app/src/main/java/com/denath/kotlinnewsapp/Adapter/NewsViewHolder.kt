package com.denath.kotlinnewsapp.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.list_news.view.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val newsTitle = view.news_title
    val sectionName = view.section_name
    val publicationDate = view.publication_date
}