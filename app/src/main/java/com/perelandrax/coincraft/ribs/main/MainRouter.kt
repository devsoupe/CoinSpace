package com.perelandrax.coincraft.ribs.main

import com.perelandrax.coincraft.ribs.navigation.NavigationBuilder
import com.perelandrax.coincraft.ribs.navigation.NavigationRouter
import com.perelandrax.coincraft.ribs.navitype.NaviTypeBuilder
import com.perelandrax.coincraft.ribs.navitype.NaviTypeRouter
import com.perelandrax.coincraft.ribs.toolbar.ToolbarBuilder
import com.perelandrax.coincraft.ribs.toolbar.ToolbarRouter
import com.uber.rib.core.ViewRouter
import kotlinx.android.synthetic.main.layout_main_rib.view.*

/**
 * Adds and removes children of {@link MainBuilder.MainScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class MainRouter(
  view: MainView,
  interactor: MainInteractor,
  component: MainBuilder.Component,
  private val toolbarBuilder: ToolbarBuilder,
  private val navigationBuilder: NavigationBuilder,
  private val naviTypeBuilder: NaviTypeBuilder
) : ViewRouter<MainView, MainInteractor, MainBuilder.Component>(view, interactor, component) {

  private var toolbarRouter: ToolbarRouter = toolbarBuilder.build(view)
  private var navigationRouter: NavigationRouter = navigationBuilder.build(view)
  private var naviTypeRouter: NaviTypeRouter = naviTypeBuilder.build()

  fun attachToolbar() {
    attachChild(toolbarRouter)
    view.toolbar.addView(toolbarRouter.view)
  }

  fun attachNavigation() {
    attachChild(navigationRouter)
    view.navigation.addView(navigationRouter.view)
  }

  fun attachNaviType() {
    attachChild(naviTypeRouter)
  }
}
