package com.perelandrax.coinspace.presentation.ribs.article

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.layout_article_rib.view.*

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
    articleLoadingView.speed = 1.25f
    articleLoadingView.visibility = View.VISIBLE
    articleLoadingView.playAnimation()
  }
}
