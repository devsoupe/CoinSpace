package com.perelandrax.coincraft.ribs.navitype

import android.view.ViewGroup
import com.perelandrax.coincraft.ribs.about.AboutBuilder
import com.perelandrax.coincraft.ribs.about.AboutRouter
import com.perelandrax.coincraft.ribs.coins.CoinsBuilder
import com.perelandrax.coincraft.ribs.coins.CoinsRouter
import com.perelandrax.coincraft.ribs.ico.IcoBuilder
import com.perelandrax.coincraft.ribs.ico.IcoRouter
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
  private val coinsBuilder: CoinsBuilder,
  private val icoBuilder: IcoBuilder,
  private val aboutBuilder: AboutBuilder
) : Router<NaviTypeInteractor, NaviTypeBuilder.Component>(interactor, component) {

  private var coinsRouter: CoinsRouter = coinsBuilder.build(parentView)
  private var icoRouter: IcoRouter = icoBuilder.build(parentView)
  private var aboutRouter: AboutRouter = aboutBuilder.build(parentView)

  fun attachCoins() {
    attachChild(coinsRouter)
    parentView.content.addView(coinsRouter.view)
  }

  fun detachCoins() {
    detachChild(coinsRouter)
    parentView.content.removeView(coinsRouter.view)
  }

  fun attachIco() {
    attachChild(icoRouter)
    parentView.content.addView(icoRouter.view)
  }

  fun detachIco() {
    detachChild(icoRouter)
    parentView.content.removeView(icoRouter.view)
  }

  fun attachAbout() {
    attachChild(aboutRouter)
    parentView.content.addView(aboutRouter.view)
  }

  fun detachAbout() {
    detachChild(aboutRouter)
    parentView.content.removeView(aboutRouter.view)
  }
}
