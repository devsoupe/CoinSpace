package com.perelandrax.coincraft.presentation.ribs.ico.tablayout

import android.view.View
import com.uber.rib.core.ViewRouter

interface TabLayoutView {

  val title: String
  val router: ViewRouter<*, *, *>
  val view: View
}

abstract class TabLayoutViewBuilder : TabLayoutView {

  override val router: ViewRouter<*, *, *>
    get() = build()

  override val view: View
    get() = router.view

  abstract fun build(): ViewRouter<*, *, *>
}
