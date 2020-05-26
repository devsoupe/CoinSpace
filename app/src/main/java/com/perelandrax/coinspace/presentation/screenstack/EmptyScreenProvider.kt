package com.perelandrax.coinspace.presentation.screenstack

import android.view.View
import android.view.ViewGroup
import com.uber.rib.core.screenstack.ViewProvider

class EmptyScreenProvider : ViewProvider() {

  override fun buildView(parentView: ViewGroup): View {
    return parentView
  }
}