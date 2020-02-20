package com.perelandrax.coinspace.presentation.ribs.splash

import android.view.View
import android.view.ViewGroup
import com.uber.rib.core.screenstack.ViewProvider

class SplashScreen(private val builder: SplashBuilder) : ViewProvider() {

  lateinit var router: SplashRouter

  override fun buildView(parentView: ViewGroup): View {
    router = builder.build(parentView)
    return router.view
  }
}