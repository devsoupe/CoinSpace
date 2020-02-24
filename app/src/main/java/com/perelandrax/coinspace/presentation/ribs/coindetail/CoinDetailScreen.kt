package com.perelandrax.coinspace.presentation.ribs.coindetail

import android.view.View
import android.view.ViewGroup
import com.perelandrax.coinspace.domain.coindetail.CoinDetail
import com.uber.rib.core.screenstack.ViewProvider

class CoinDetailScreen(private val builder: CoinDetailBuilder) : ViewProvider() {

  lateinit var router: CoinDetailRouter
  lateinit var coinId: String

  override fun buildView(parentView: ViewGroup): View {
    router = builder.build(parentView, coinId)
    return router.view
  }
}