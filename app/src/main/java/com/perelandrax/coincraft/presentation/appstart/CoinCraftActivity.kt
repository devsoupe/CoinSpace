package com.perelandrax.coincraft.presentation.appstart

import android.view.ViewGroup
import com.perelandrax.coincraft.presentation.ribs.root.RootBuilder
import com.perelandrax.coincraft.presentation.ribs.root.RootBuilder.ParentComponent
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class CoinCraftActivity : RibActivity() {

  private val TAG = javaClass.simpleName

  override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> {
    val rootBuilder = RootBuilder(object : ParentComponent {})
    return rootBuilder.build(parentViewGroup)
  }
}
