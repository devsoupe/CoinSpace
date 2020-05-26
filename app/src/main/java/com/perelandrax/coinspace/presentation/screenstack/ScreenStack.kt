package com.perelandrax.coinspace.presentation.screenstack

import com.uber.rib.core.screenstack.ViewProvider

interface ScreenStack {

  fun addEmptyScreen()

  fun replace(viewProvider: ViewProvider)
  fun push(viewProvider: ViewProvider)
  fun present(viewProvider: ViewProvider)
  fun stack(viewProvider: ViewProvider)
  fun back(): Boolean

  fun isAnimating(): Boolean
  fun getScreenStackCount(): Int
}