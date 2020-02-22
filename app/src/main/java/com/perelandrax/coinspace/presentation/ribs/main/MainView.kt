package com.perelandrax.coinspace.presentation.ribs.main

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.orhanobut.logger.Logger
import com.perelandrax.coinspace.presentation.ribslib.ObjectAnimatorFrameLayout

/**
 * Top level view for {@link MainBuilder.MainScope}.
 */
class MainView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  ObjectAnimatorFrameLayout(context, attrs, defStyle), MainInteractor.MainPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()
    Logger.i("onFinishInflate")
  }
}
