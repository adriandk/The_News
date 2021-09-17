package com.adrian.thenews.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.thenews.R
import com.adrian.thenews.core.adapter.HomeAdapter
import com.adrian.thenews.core.data.Resource
import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModel()
    private lateinit var newsAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            newsAdapter = HomeAdapter()

            getData("corona")
            swipe_refresh.setOnRefreshListener {
                getData("corona")
                swipe_refresh.isRefreshing = false
            }

            rv_news.layoutManager = LinearLayoutManager(context)
            rv_news.setHasFixedSize(true)
            rv_news.adapter = newsAdapter
        }
    }

    private fun getData(search: String) {
        newsViewModel.news(search).observe(viewLifecycleOwner, newsObserver)
    }

    private val newsObserver = Observer<Resource<PagedList<NewsEntity>>> {
        if (it != null) {
            when (it) {
                is Resource.Loading -> loading_view.visibility = View.VISIBLE
                is Resource.Success -> {
                    newsAdapter.submitList(it.data)
                    newsAdapter.setData(it.data)
                    rv_news.visibility = View.VISIBLE
                    loading_view.visibility = View.GONE
                    error_view.visibility = View.GONE
                    newsAdapter.onItemClick = {
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
                }
                is Resource.Error -> {
                    error_view.visibility = View.VISIBLE
                    loading_view.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.search_menu, menu)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search_button).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))

        searchView.queryHint = "Search Hot News"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                newsViewModel.news(query.toString()).observe(viewLifecycleOwner, newsObserver)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    private fun setStatusBookmark(bookmarkStatus: Boolean) {
        if (bookmarkStatus) {

        } else {

        }
    }

}