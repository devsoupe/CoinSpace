package com.perelandrax.coinspace.presentation.ribs.coinwebsite

import android.view.View
import android.view.ViewGroup
import com.perelandrax.coinspace.domain.CoinWebsite
import com.uber.rib.core.screenstack.ViewProvider

class CoinWebsiteScreen(private val builder: CoinWebsiteBuilder) : ViewProvider() {

  lateinit var router: CoinWebsiteRouter
  lateinit var coinWebsite: CoinWebsite

  override fun buildView(parentView: ViewGroup): View {
    router = builder.build(parentView, coinWebsite)
    return router.view
  }
}