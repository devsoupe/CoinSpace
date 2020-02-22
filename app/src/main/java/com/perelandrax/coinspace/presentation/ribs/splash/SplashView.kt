package com.perelandrax.coinspace.presentation.ribs.splash

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.layout_splash_rib.view.*

/**
 * Top level view for {@link SplashBuilder.SplashScope}.
 */
class SplashView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), SplashInteractor.SplashPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()
    Logger.i("onFinishInflate")

    setupLoadingView()
  }

  private fun setupLoadingView() {
    splashLoadingView.speed = 1.5f
  }

  override fun showLoading() {
    splashLoadingView.visibility = View.VISIBLE
    splashLoadingView.playAnimation()
  }

  override fun hideLoading() {
    splashLoadingView.visibility = View.GONE
    splashLoadingView.cancelAnimation()
  }

  override fun showError() {

  }
}
