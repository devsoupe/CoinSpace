package com.perelandrax.coinspace.presentation.screenstack

import com.uber.rib.core.screenstack.ViewProvider

interface ScreenStack {

  fun replace(viewProvider: ViewProvider)
  fun push(viewProvider: ViewProvider)
  fun present(viewProvider: ViewProvider)
  fun back(): Boolean
}