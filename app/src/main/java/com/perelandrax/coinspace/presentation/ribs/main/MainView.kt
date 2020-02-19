package com.perelandrax.coinspace.presentation.ribs.main

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Top level view for {@link MainBuilder.MainScope}.
 */
class MainView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), MainInteractor.MainPresenter {

  fun getXFraction(): Float {
    return x / width
  }

  fun setXFraction(xFraction: Float) {
    val width = width
    x = if (width > 0) xFraction * width else (-9999).toFloat()
  }
}
