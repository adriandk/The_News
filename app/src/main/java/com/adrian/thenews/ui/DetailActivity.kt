package com.adrian.thenews.ui

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.adrian.thenews.R
import com.adrian.thenews.core.data.source.local.entity.NewsEntity
import com.adrian.thenews.core.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val NEWS_URL = "NEWS_URL"
        const val NEWS_TITLE = "NEWS_DATA"
        const val NEWS_DESC = "NEWS_DATA"
        const val NEWS_STATUS = "NEWS_STATUS"
        const val NEWS_IMAGE = "NEWS_DATA"
        const val NEWS_CONTENT = "NEWS_DATA"
        const val NEWS_DATE = "NEWS_DATA"
        const val NEWS_SOURCE = "NEWS_DATA"
        const val NEWS_ID = "NEWS_DATA"
    }

    private val detailViewModel: DetailViewModel by viewModel()

    private lateinit var pageUrl: String
    private var newsId: Int? = null
    private lateinit var newsTitle: String
    private lateinit var newsSource: String
    private lateinit var newsDescription: String
    private lateinit var newsImage: String
    private lateinit var newsDate: String
    private lateinit var newsContent: String
    private var newsStatus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        pageUrl = intent.getStringExtra(NEWS_URL).toString()
        newsId = intent.getIntExtra(NEWS_ID, 0)
        newsTitle = intent.getStringExtra(NEWS_TITLE).toString()
        newsSource = intent.getStringExtra(NEWS_SOURCE).toString()
        newsDescription = intent.getStringExtra(NEWS_DESC).toString()
        newsImage = intent.getStringExtra(NEWS_IMAGE).toString()
        newsDate = intent.getStringExtra(NEWS_DATE).toString()
        newsContent = intent.getStringExtra(NEWS_CONTENT).toString()
        newsStatus = intent.getBooleanExtra(NEWS_STATUS, false)

        val dataNews = NewsEntity(
            newsId,
            newsTitle,
            newsSource,
            newsDescription,
            pageUrl,
            newsImage,
            newsDate,
            newsContent,
            newsStatus
        )

        var bookmarkStatus = newsStatus
        setBookmarkStatus(bookmarkStatus)
        bookmark_button.setOnClickListener {
            bookmarkStatus = !bookmarkStatus
//            Toast.makeText(this, "$dataNews", Toast.LENGTH_SHORT).show()
            Log.e("DetailActivity", "$dataNews")
//            detailViewModel.setBookmarkNews(dataNews, bookmarkStatus)
//            setBookmarkStatus(bookmarkStatus)
        }

        initWebView()
        AppWebViewClients(progress_bar)
        loadUrl(pageUrl)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        content.detail_view.settings.javaScriptEnabled = true
        content.detail_view.settings.loadWithOverviewMode = true
        content.detail_view.settings.useWideViewPort = true
        content.detail_view.settings.domStorageEnabled = true

        content.detail_view.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
    }

    class AppWebViewClients(private val progressBar: ProgressBar) : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }

        init {
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && content.detail_view.canGoBack()) {
            content.detail_view.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun loadUrl(pageUrl: String) {
        content.detail_view.loadUrl(pageUrl)
    }

    private fun setBookmarkStatus(statusFavorite: Boolean) {
        if (statusFavorite) {
            bookmark_button.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bookmark_icon
                )
            )
        } else {
            bookmark_button.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bookmark_border_icon
                )
            )
        }
    }

}