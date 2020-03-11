package com.perelandrax.coinspace.presentation.ribs.main

import android.content.Context
import android.util.AttributeSet
import com.perelandrax.coinspace.presentation.screenstack.AnimationFrameLayout
import com.perelandrax.coinspace.utilities.ActivityUtil
import com.perelandrax.coinspace.utilities.StatusBarUtil
import kotlinx.android.synthetic.main.layout_main_rib.view.*

/**
 * Top level view for {@link MainBuilder.MainScope}.
 */
class MainView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  AnimationFrameLayout(context, attrs, defStyle), MainInteractor.MainPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()
  }
}
