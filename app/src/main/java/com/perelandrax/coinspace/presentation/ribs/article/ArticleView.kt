package com.perelandrax.coinspace.presentation.ribs.article

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.layout_article_rib.view.*
import kotlinx.android.synthetic.main.layout_loading_bar.view.*

/**
 * Top level view for {@link ArticleBuilder.ArticleScope}.
 */
class ArticleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), ArticleInteractor.ArticlePresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()
    setupLoadingView()
  }

  private fun setupLoadingView() {
    loadingView.speed = 1.25f
  }

  override fun showLoading() {
    loadingLayout.visibility = View.VISIBLE
    loadingView.playAnimation()
  }

  override fun hideLoading() {
    loadingLayout.visibility = View.GONE
    loadingView.cancelAnimation()
  }
}
