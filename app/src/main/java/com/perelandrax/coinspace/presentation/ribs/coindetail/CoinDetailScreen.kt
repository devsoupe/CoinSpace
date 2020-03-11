package com.perelandrax.coinspace.presentation.ribs.coindetail

import android.view.View
import android.view.ViewGroup
import com.perelandrax.coinspace.utilities.ActivityUtil
import com.perelandrax.coinspace.utilities.StatusBarUtil
import com.uber.rib.core.screenstack.ViewProvider

class CoinDetailScreen(private val builder: CoinDetailBuilder) : ViewProvider() {

  lateinit var router: CoinDetailRouter
  lateinit var coinId: String

  override fun buildView(parentView: ViewGroup): View {
    router = builder.build(parentView, coinId)
    return router.view
  }

  override fun onViewAppeared() {
    super.onViewAppeared()

    router.view.apply {
      StatusBarUtil.setColor(
        ActivityUtil.scanForAppCompatActivity(context),
        router.view.getHeaderColor(),
        StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA)
    }
  }
}