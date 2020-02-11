package com.perelandrax.coincraft.presentation.ribs.active

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.layout_active_rib.view.*

/**
 * Top level view for {@link ActiveBuilder.ActiveScope}.
 */
class ActiveView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), ActiveInteractor.ActivePresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()
    setupLoadingView()
  }

  private fun setupLoadingView() {
    loadingView.speed = 1.25f
    loadingView.visibility = View.VISIBLE
    loadingView.playAnimation()
  }
}
