package com.perelandrax.coincraft.ribs.ico.presentation.tablayout

import android.view.ViewGroup
import com.perelandrax.coincraft.ribs.upcoming.UpcomingBuilder
import com.uber.rib.core.ViewRouter

class UpcomingAdapter(val builder: UpcomingBuilder, val parentViewGroup: ViewGroup) : TabLayoutViewBuilder() {

  override val title: String
    get() = "UPCOMING"

  override fun build(): ViewRouter<*, *, *> {
    return builder.build(parentViewGroup)
  }
}