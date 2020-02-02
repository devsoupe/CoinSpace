package com.perelandrax.coincraft.ribs.navitype

import android.view.ViewGroup
import com.perelandrax.coincraft.ribs.coins.CoinsBuilder
import com.perelandrax.coincraft.ribs.coins.CoinsRouter
import com.uber.rib.core.Router
import kotlinx.android.synthetic.main.main_rib.view.content

/**
 * Adds and removes children of {@link NaviTypeBuilder.NaviTypeScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class NaviTypeRouter(
  interactor: NaviTypeInteractor,
  component: NaviTypeBuilder.Component,
  private val parentView: ViewGroup,
  private val coinsBuilder: CoinsBuilder
) : Router<NaviTypeInteractor, NaviTypeBuilder.Component>(interactor, component) {

  private var coinsRouter: CoinsRouter = coinsBuilder.build(parentView)

  fun attachCoins() {
    attachChild(coinsRouter)
    parentView.content.addView(coinsRouter.view)
  }

  fun detachCoins() {
    detachChild(coinsRouter)
    parentView.content.removeView(coinsRouter.view)
  }
}
