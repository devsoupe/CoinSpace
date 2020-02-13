package com.perelandrax.coinspace.presentation.ribs.splash

import com.perelandrax.coinspace.presentation.ribs.main.MainBuilder
import com.perelandrax.coinspace.presentation.ribs.main.MainRouter
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link SplashBuilder.SplashScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class SplashRouter(view: SplashView, interactor: SplashInteractor, component: SplashBuilder.Component,
                   mainBuilder: MainBuilder) :
  ViewRouter<SplashView, SplashInteractor, SplashBuilder.Component>(view, interactor, component) {

  private var mainRouter: MainRouter = mainBuilder.build(view)

  fun attachMain() {
    attachChild(mainRouter)
    view.addView(mainRouter.view)
  }
}
