package com.perelandrax.coincraft.presentation.ribs.ico.presentation.tablayout

import android.view.ViewGroup
import com.perelandrax.coincraft.presentation.ribs.active.ActiveBuilder
import com.uber.rib.core.ViewRouter

class ActiveAdapter(val builder: ActiveBuilder, val parentViewGroup: ViewGroup) : TabLayoutViewBuilder() {

  override val title: String
    get() = "ACTIVE"

  override fun build(): ViewRouter<*, *, *> {
    return builder.build(parentViewGroup)
  }
}