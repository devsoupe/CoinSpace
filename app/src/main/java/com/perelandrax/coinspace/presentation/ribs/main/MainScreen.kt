package com.perelandrax.coinspace.presentation.ribs.main

import android.view.View
import android.view.ViewGroup
import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.utilities.ActivityUtil
import com.perelandrax.coinspace.utilities.StatusBarUtil
import com.uber.rib.core.screenstack.ViewProvider
import kotlinx.android.synthetic.main.layout_main_rib.view.*

class MainScreen(private val builder: MainBuilder) : ViewProvider() {

  lateinit var router: MainRouter

  override fun buildView(parentView: ViewGroup): View {
    router = builder.build(parentView)
    return router.view
  }

  override fun onViewAppeared() {
    super.onViewAppeared()

    router.view.apply {
      StatusBarUtil.setColor(
        ActivityUtil.scanForAppCompatActivity(context),
        resources.getColor(R.color.colorPrimaryDark),
        StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA)
    }
  }
}