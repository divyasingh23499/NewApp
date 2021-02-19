package com.example.newsly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebViewDatabase
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        Log.d("TAG", "onCreate()")

        val url = intent.getStringExtra("URL")
        if (url != null) {
            webView.settings.javaScriptEnabled = true

            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                    webView.visibility = View.VISIBLE
                }
            }

            Log.d("URL", "url : $url")
            webView.loadUrl(url)
        }
    }
}