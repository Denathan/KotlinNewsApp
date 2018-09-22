package com.denath.kotlinnewsapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_news.view.*

class NewsAdapter(val newsList: ArrayList<NewsDto>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return newsList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsDto = newsList[position]
        holder.newsTitle?.text = newsDto.title
        holder.sectionName?.text = newsDto.sectionName
        holder.authorName?.text = newsDto.author
        holder.publicationDate?.text = newsDto.webPublicationDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_news, parent, false))
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val newsTitle = view.news_title
    val sectionName = view.section_name
    val authorName = view.author_name
    val publicationDate = view.publication_date
}
