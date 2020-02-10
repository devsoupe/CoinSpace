package com.perelandrax.coincraft.presentation.ribs.root

import com.perelandrax.coincraft.presentation.ribs.main.MainBuilder
import com.perelandrax.coincraft.presentation.ribs.main.MainRouter
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(view: RootView, interactor: RootInteractor, component: RootBuilder.Component,
                 mainBuilder: MainBuilder) :
  ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

  private var mainRouter: MainRouter = mainBuilder.build(view)

  fun attachMain() {
    attachChild(mainRouter)
    view.addView(mainRouter.view)
  }
}
