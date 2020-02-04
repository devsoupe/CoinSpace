package com.perelandrax.coincraft.ribs.coins

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.coins_rib.view.bgAnimationView
import kotlinx.android.synthetic.main.coins_rib.view.loadingView

/**
 * Top level view for {@link CoinsBuilder.CoinsScope}.
 */
class CoinsView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), CoinsInteractor.CoinsPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()

    setupLoading()
  }

  private fun setupLoading() {
    bgAnimationView.speed = 3.5f
    loadingView.speed = 1.25f
  }

  override fun showLoading() {
    loadingView.visibility = View.VISIBLE
    loadingView.playAnimation()
  }

  override fun hideLoading() {
    loadingView.visibility = View.GONE
    loadingView.cancelAnimation()
  }
}
