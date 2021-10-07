package com.adrian.thenews.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.thenews.R
import com.adrian.thenews.core.adapter.HomeAdapter
import com.adrian.thenews.core.data.Resource
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
            getData("corona", false)
            swipe_refresh.setOnRefreshListener {
                getData("corona", false)
                swipe_refresh.isRefreshing = false
            }
            newsAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.NEWS_DATA, it)
                startActivity(intent)
            }
            rv_news.layoutManager = LinearLayoutManager(context)
            rv_news.setHasFixedSize(true)
            rv_news.adapter = newsAdapter
        }
    }

    private fun getData(search: String, type: Boolean) {
        newsViewModel.news(search).observe(viewLifecycleOwner, { news ->
            if (news != null) {
                when (news) {
                    is Resource.Loading -> {
                        loading_view.visibility = View.VISIBLE
                        error_view.visibility = View.GONE
                        rv_news.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        loading_view.visibility = View.GONE
                        error_view.visibility = View.VISIBLE
                        rv_news.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        loading_view.visibility = View.GONE
                        error_view.visibility = View.GONE
                        rv_news.visibility = View.VISIBLE
                        if (type) {
                            newsViewModel.searchNews(search).observe(viewLifecycleOwner, {
                                Log.e("HomeFragment", it.toString())
                                if (it.isNotEmpty()) {
                                    newsAdapter.setData(it)
                                } else {
                                    newsAdapter.setData(news.data)
                                }
                            })
                        } else {
                            newsAdapter.setData(news.data)
                        }
                    }
                }
            }
        })
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
                getData(query.toString(), true)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

}