package com.perelandrax.coincraft.ribs.main

import com.perelandrax.coincraft.ribs.navigation.NavigationBuilder
import com.perelandrax.coincraft.ribs.navigation.NavigationRouter
import com.uber.rib.core.ViewRouter
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
  private val navigationBuilder: NavigationBuilder
) : ViewRouter<MainView, MainInteractor, MainBuilder.Component>(view, interactor, component) {

  private var navigationRouter: NavigationRouter? = null

  fun attachNavigation() {
    navigationRouter = navigationBuilder.build(view)
    attachChild(navigationRouter)
    view.navigation.addView(navigationRouter?.view)
  }
}
