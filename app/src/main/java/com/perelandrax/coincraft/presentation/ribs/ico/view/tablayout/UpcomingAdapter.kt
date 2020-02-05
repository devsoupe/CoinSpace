package com.perelandrax.coincraft.presentation.ribs.ico.view.tablayout

import android.view.ViewGroup
import com.perelandrax.coincraft.presentation.ribs.upcoming.UpcomingBuilder
import com.uber.rib.core.ViewRouter

class UpcomingAdapter(val builder: UpcomingBuilder, val parentViewGroup: ViewGroup) : TabLayoutViewBuilder() {

  override val title: String
    get() = "UPCOMING"

  override fun build(): ViewRouter<*, *, *> {
    return builder.build(parentViewGroup)
  }
}