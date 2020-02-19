package com.perelandrax.coinspace.presentation.ribs.coindetail

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.layout_coin_detail_rib.view.*


/**
 * Top level view for {@link CoinDetailBuilder.CoinDetailScope}.
 */
class CoinDetailView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), CoinDetailInteractor.CoinDetailPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()

    setupLoadingView()
  }

  private fun setupLoadingView() {
    coinDetailLoadingView.speed = 1.25f
  }

  override fun showLoading() {
    coinDetailLoadingView.visibility = View.VISIBLE
    coinDetailLoadingView.playAnimation()
  }

  override fun hideLoading() {
    coinDetailLoadingView.visibility = View.GONE
    coinDetailLoadingView.cancelAnimation()
  }

  fun getXFraction(): Float {
    return x / width
  }


  fun setXFraction(xFraction: Float) {
    val width = width
    x = if (width > 0) xFraction * width else (-9999).toFloat()
  }
}
