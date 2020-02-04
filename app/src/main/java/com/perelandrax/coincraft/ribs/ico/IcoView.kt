package com.perelandrax.coincraft.ribs.ico

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.coins_rib.view.loadingView

/**
 * Top level view for {@link IcoBuilder.IcoScope}.
 */
class IcoView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), IcoInteractor.IcoPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()

    setupLoadingView()
  }

  private fun setupLoadingView() {
    loadingView.speed = 1.25f
  }
}
