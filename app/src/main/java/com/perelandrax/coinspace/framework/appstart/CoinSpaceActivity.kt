package com.perelandrax.coinspace.framework.appstart

import android.view.ViewGroup
import com.perelandrax.coinspace.presentation.ribs.root.RootBuilder
import com.perelandrax.coinspace.presentation.ribs.root.RootBuilder.ParentComponent
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class CoinSpaceActivity : RibActivity() {

  private val TAG = javaClass.simpleName

  override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> {
    val rootBuilder = RootBuilder(object : ParentComponent {})
    return rootBuilder.build(this, parentViewGroup)
  }
}
