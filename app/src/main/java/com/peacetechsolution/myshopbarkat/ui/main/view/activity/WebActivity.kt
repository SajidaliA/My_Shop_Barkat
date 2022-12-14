package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.ActivityWebBinding
import com.peacetechsolution.myshopbarkat.util.Constant.URL_PATH
import com.peacetechsolution.myshopbarkat.util.setStatusBarColor


class WebActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setStatusBarColor(R.color.landingStatusBar)
        intent.getStringExtra(URL_PATH)?.let { mBinding.webView.loadUrl(it) }

        mBinding.webView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url)
                return false // then it is not handled by default action
            }
        })

    }
}