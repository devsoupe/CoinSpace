package com.perelandrax.coincraft.ribs.main

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link MainBuilder.MainScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class MainRouter(
  view: MainView,
  interactor: MainInteractor,
  component: MainBuilder.Component
) : ViewRouter<MainView, MainInteractor, MainBuilder.Component>(view, interactor, component)
