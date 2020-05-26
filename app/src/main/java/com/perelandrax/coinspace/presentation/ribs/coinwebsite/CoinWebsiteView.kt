package com.perelandrax.coinspace.presentation.ribs.coinwebsite

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.perelandrax.coinspace.presentation.screenstack.AnimationFrameLayout
import kotlinx.android.synthetic.main.layout_coin_website_rib.view.*
import kotlinx.android.synthetic.main.layout_loading_bar.view.*

/**
 * Top level view for {@link CoinWebsiteBuilder.CoinWebsiteScope}.
 */
class CoinWebsiteView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  AnimationFrameLayout(context, attrs, defStyle), CoinWebsiteInteractor.CoinWebsitePresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()

    setupLoadingView()
    setupWebView()
  }

  private fun setupLoadingView() {
    loadingView.speed = 1.5f
  }

  private fun setupWebView() {
    webview.settings.javaScriptEnabled = true
    webview.webViewClient = CoinWebViewClient()
  }

  fun showLoading() {
    loadingLayout.visibility = View.VISIBLE
    loadingView.playAnimation()
  }

  fun hideLoading() {
    loadingLayout.visibility = View.GONE
    loadingView.cancelAnimation()
  }

  override fun updateTitle(title: String) {
    titleToolbar.text = title
  }

  override fun showWebsite(url: String) {
    webview.loadUrl(url)
  }

  inner class CoinWebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
      showLoading()
      view?.loadUrl(url)
      return true
    }

    override fun onPageFinished(view: WebView?, url: String?) {
      super.onPageFinished(view, url)
      hideLoading()
    }
  }
}
