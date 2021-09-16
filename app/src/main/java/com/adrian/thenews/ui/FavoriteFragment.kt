package com.adrian.thenews.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            if (it.isEmpty()) {
                Log.e("Bookmark", it.toString())
                bookmarkAdapter.submitList(it)
                bookmarkAdapter.setData(it)
                bookmarkAdapter.onItemClick = {
                    val intent = Intent(activity, DetailActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(activity, "null", Toast.LENGTH_SHORT).show()
                empty_view.visibility = View.VISIBLE
            }
        })
    }

}