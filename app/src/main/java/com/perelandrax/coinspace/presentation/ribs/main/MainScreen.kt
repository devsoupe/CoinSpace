package com.perelandrax.coinspace.presentation.ribs.main

import android.view.View
import android.view.ViewGroup
import com.uber.rib.core.screenstack.ViewProvider


class MainScreen(private val builder: MainBuilder) : ViewProvider() {

  lateinit var router: MainRouter

  override fun buildView(parentView: ViewGroup): View {
    router = builder.build(parentView)
    return router.view
  }
}