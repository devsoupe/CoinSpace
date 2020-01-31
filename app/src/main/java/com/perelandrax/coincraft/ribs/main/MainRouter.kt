package com.perelandrax.coincraft.ribs.main

import com.perelandrax.coincraft.ribs.mainBottomTab.MainBottomTabBuilder
import com.perelandrax.coincraft.ribs.mainBottomTab.MainBottomTabRouter
import com.uber.rib.core.ViewRouter
import kotlinx.android.synthetic.main.main_rib.view.mainBottomTab

/**
 * Adds and removes children of {@link MainBuilder.MainScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class MainRouter(
  view: MainView,
  interactor: MainInteractor,
  component: MainBuilder.Component,
  private val mainBottomTabBuilder: MainBottomTabBuilder
) : ViewRouter<MainView, MainInteractor, MainBuilder.Component>(view, interactor, component) {

  private var mainBottomTabRouter: MainBottomTabRouter? = null

  fun attachMainBottomTab() {
    mainBottomTabRouter = mainBottomTabBuilder.build(view)
    attachChild(mainBottomTabRouter)
    view.mainBottomTab.addView(mainBottomTabRouter?.view)
  }
}
