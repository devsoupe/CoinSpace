package com.perelandrax.coinspace.presentation.ribs.coindetail

import android.view.View
import android.view.ViewGroup
import com.uber.rib.core.screenstack.ViewProvider

class CoinDetailScreen(private val builder: CoinDetailBuilder) : ViewProvider() {

  lateinit var router: CoinDetailRouter

  override fun buildView(parentView: ViewGroup): View {
    router = builder.build(parentView)
    return router.view
  }
}