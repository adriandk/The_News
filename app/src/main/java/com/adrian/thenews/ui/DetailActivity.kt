package com.adrian.thenews.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.adrian.thenews.R
import com.adrian.thenews.core.domain.model.News
import com.adrian.thenews.core.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val NEWS_DATA = "NEWS_DATA"
    }

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val newsData = intent.getParcelableExtra<News>(NEWS_DATA)

        var bookmarkStatus = newsData!!.isFavorite
        setBookmarkStatus(bookmarkStatus)
        bookmark_button.setOnClickListener {
            bookmarkStatus = !bookmarkStatus
            detailViewModel.setBookmarkNews(newsData, bookmarkStatus)
            setBookmarkStatus(bookmarkStatus)
        }

        share_button.setOnClickListener {
            shareNews(newsData)
        }

        initWebView()
        AppWebViewClients(progress_bar)
        loadUrl(newsData.url)
    }

    private fun shareNews(newsData: News) {
        val share = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "${newsData.newsTitle}, ${newsData.url}, Source of News : ${newsData.sourceNews}"
            )
            type = "text/html"
        }
        val shareIntent = Intent.createChooser(share, "Share news to...")
        startActivity(shareIntent)
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