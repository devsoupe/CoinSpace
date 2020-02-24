package com.perelandrax.coinspace.presentation.screenstack

import com.uber.rib.core.screenstack.ViewProvider

class ScreenProvider(val viewProvider: ViewProvider, val viewType: ViewType) {

  enum class ViewType {
    REPLACE,
    PUSH,
    PRESENT
  }
}