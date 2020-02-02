package com.perelandrax.coincraft.ribs.main

import com.perelandrax.coincraft.ribs.navigation.NavigationBuilder
import com.perelandrax.coincraft.ribs.navigation.NavigationRouter
import com.perelandrax.coincraft.ribs.navitype.NaviTypeBuilder
import com.perelandrax.coincraft.ribs.navitype.NaviTypeRouter
import com.uber.rib.core.ViewRouter
import kotlinx.android.synthetic.main.main_rib.view.content
import kotlinx.android.synthetic.main.main_rib.view.navigation

/**
 * Adds and removes children of {@link MainBuilder.MainScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class MainRouter(
  view: MainView,
  interactor: MainInteractor,
  component: MainBuilder.Component,
  private val navigationBuilder: NavigationBuilder,
  private val naviTypeBuilder: NaviTypeBuilder
) : ViewRouter<MainView, MainInteractor, MainBuilder.Component>(view, interactor, component) {

  private var navigationRouter: NavigationRouter = navigationBuilder.build(view)
  private var naviTypeRouter: NaviTypeRouter = naviTypeBuilder.build()

  fun attachNavigation() {
    attachChild(navigationRouter)
    view.navigation.addView(navigationRouter.view)
  }

  fun attachNaviType() {
    attachChild(naviTypeRouter)
  }
}
