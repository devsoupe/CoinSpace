package com.perelandrax.coinspace.presentation.ribs.root

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.orhanobut.logger.Logger

/**
 * Top level view for {@link RootBuilder.RootScope}.
 */
class RootView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), RootInteractor.RootPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()
    Logger.t("RIBS").i("onFinishInflate")
  }
}
