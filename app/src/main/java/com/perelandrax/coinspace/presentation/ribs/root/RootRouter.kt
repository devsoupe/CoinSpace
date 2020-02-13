package com.perelandrax.coinspace.presentation.ribs.root

import com.perelandrax.coinspace.presentation.ribs.splash.SplashBuilder
import com.perelandrax.coinspace.presentation.ribs.splash.SplashRouter
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(view: RootView, interactor: RootInteractor, component: RootBuilder.Component,
                 splashBuilder: SplashBuilder) :
  ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

  private var splashRouter: SplashRouter = splashBuilder.build(view)

  fun attachSplash() {
    attachChild(splashRouter)
    view.addView(splashRouter.view)
  }
}
