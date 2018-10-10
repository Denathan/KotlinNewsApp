package com.denath.kotlinnewsapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.denath.kotlinnewsapp.models.Results
import com.denath.kotlinnewsapp.R

class NewsAdapter(var newsList: List<Results>, var context: Context) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holderNews: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holderNews.newsTitle.text = news.webTitle
        holderNews.sectionName.text = news.sectionName
        holderNews.publicationDate.text = news.webPublicationDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_news, parent, false))
    }
}
