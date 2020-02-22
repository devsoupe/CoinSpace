package com.perelandrax.coinspace.presentation.ribs.main

import com.orhanobut.logger.Logger
import com.perelandrax.coinspace.presentation.ribs.navigation.NavigationBuilder
import com.perelandrax.coinspace.presentation.ribs.navigation.NavigationRouter
import com.perelandrax.coinspace.presentation.ribs.navitype.NaviTypeBuilder
import com.perelandrax.coinspace.presentation.ribs.navitype.NaviTypeRouter
import com.perelandrax.coinspace.presentation.ribs.toolbar.ToolbarBuilder
import com.perelandrax.coinspace.presentation.ribs.toolbar.ToolbarRouter
import com.perelandrax.coinspace.presentation.ribslib.ScreenViewRouter
import kotlinx.android.synthetic.main.layout_main_rib.view.*

/**
 * Adds and removes children of {@link MainBuilder.MainScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class MainRouter(view: MainView, interactor: MainInteractor, component: MainBuilder.Component,
                 toolbarBuilder: ToolbarBuilder,
                 navigationBuilder: NavigationBuilder,
                 naviTypeBuilder: NaviTypeBuilder) :
  ScreenViewRouter<MainView, MainInteractor, MainBuilder.Component>(view, interactor, component) {

  private var toolbarRouter: ToolbarRouter = toolbarBuilder.build(view)
  private var navigationRouter: NavigationRouter = navigationBuilder.build(view)
  private var naviTypeRouter: NaviTypeRouter = naviTypeBuilder.build()

  override fun willAttach() {
    super.willAttach()
    Logger.i("willAttach")
  }

  override fun willDetach() {
    super.willDetach()
    Logger.i("willDetach")
  }

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
