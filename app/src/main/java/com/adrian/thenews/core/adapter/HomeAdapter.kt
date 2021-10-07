package com.adrian.thenews.core.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.thenews.R
import com.adrian.thenews.core.domain.model.News
import com.adrian.thenews.core.utils.Formatter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.news_item.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val newsList = ArrayList<News>()
    var onItemClick: ((News) ->  Unit)? = null

    fun setData(newListData: List<News>?) {
        newsList.clear()
        if(newListData == null) return
        newsList.clear()
        newsList.addAll(newListData)
        Log.e("HomeAdapter", newListData.toString())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {
            with(itemView) {
                news_title.text = news.newsTitle
                source_name.text = news.sourceNews
                date_news.text = Formatter.getDate(news.publishDate.toString())
                Glide.with(itemView.context)
                    .load(news.imageUrl)
                    .apply(RequestOptions.placeholderOf(R.drawable.image_icon))
                    .error(R.drawable.brokenimage_icon)
                    .into(news_image)
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(newsList[adapterPosition])
            }
        }
    }

}