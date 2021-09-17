package com.adrian.thenews.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.thenews.R
import com.adrian.thenews.core.adapter.HomeAdapter
import com.adrian.thenews.core.viewmodel.BookmarkViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val bookmarkViewModel: BookmarkViewModel by viewModel()
    private lateinit var bookmarkAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            bookmarkAdapter = HomeAdapter()
            setDataToAdapter()
            rv_favorite.layoutManager = LinearLayoutManager(context)
            rv_favorite.setHasFixedSize(true)
            rv_favorite.adapter = bookmarkAdapter
        }
    }

    private fun setDataToAdapter() {
        bookmarkViewModel.bookmarkNews.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                bookmarkAdapter.submitList(it)
                bookmarkAdapter.setData(it)
                bookmarkAdapter.onItemClick = {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.NEWS_ID, it.newsId)
                    intent.putExtra(DetailActivity.NEWS_TITLE, it.newsTitle)
                    intent.putExtra(DetailActivity.NEWS_SOURCE, it.sourceNews)
                    intent.putExtra(DetailActivity.NEWS_DATE, it.publishDate)
                    intent.putExtra(DetailActivity.NEWS_DESC, it.newsDescription)
                    intent.putExtra(DetailActivity.NEWS_CONTENT, it.content)
                    intent.putExtra(DetailActivity.NEWS_URL, it.url)
                    intent.putExtra(DetailActivity.NEWS_IMAGE, it.imageUrl)
                    intent.putExtra(DetailActivity.NEWS_STATUS, it.isFavorite)
                    startActivity(intent)
                }
            } else {
                empty_view.visibility = View.VISIBLE
            }
        })
    }

}