package com.adrian.thenews.core.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adrian.thenews.R
import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.utils.Formatter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.news_item.view.*

class HomeAdapter : PagedListAdapter<NewsEntity, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((NewsEntity) -> Unit)? = null
    private var newsList = ArrayList<NewsEntity>()

    fun setData(newListData: List<NewsEntity>?) {
        if (newListData == null) return
        Log.e("HomeAdapter", newListData.toString())
        newsList.clear()
        newsList.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataNews: NewsEntity) {
            with(itemView) {
                news_title.text = dataNews.newsTitle.toString()
                source_name.text = dataNews.sourceNews.toString()
                date_news.text = Formatter.getDate(dataNews.publishDate.toString())
                Glide.with(itemView.context)
                    .load(dataNews.imageUrl)
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

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsEntity>() {
            override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean =
                oldItem.newsId == newItem.newsId

            override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean =
                oldItem == newItem

        }
    }
}