package com.perelandrax.coinspace.presentation.ribs.news.tablayout

import android.view.ViewGroup
import com.perelandrax.coinspace.presentation.ribs.social.SocialBuilder
import com.uber.rib.core.ViewRouter

class SocialAdapter(val builder: SocialBuilder, val parentViewGroup: ViewGroup) : TabLayoutViewBuilder() {

  override val title: String
    get() = "SOCIAL"

  override fun build(): ViewRouter<*, *, *> {
    return builder.build(parentViewGroup)
  }
}