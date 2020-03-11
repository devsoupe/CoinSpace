package com.perelandrax.coinspace.presentation.ribs.root

import com.orhanobut.logger.Logger
import com.perelandrax.coinspace.presentation.ribs.splash.SplashBuilder
import com.perelandrax.coinspace.presentation.ribs.splash.SplashScreen
import com.perelandrax.coinspace.presentation.screenstack.ScreenStack
import com.perelandrax.coinspace.presentation.screenstack.ScreenViewRouter
import io.reactivex.disposables.CompositeDisposable

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(view: RootView, interactor: RootInteractor, component: RootBuilder.Component,
                 private val screenStack: ScreenStack,
                 splashBuilder: SplashBuilder) :
  ScreenViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

  private val disposables = CompositeDisposable()
  private var splashScreen = SplashScreen(splashBuilder)

  override fun willAttach() {
    super.willAttach()

    disposables.add(splashScreen.lifecycle()
      .subscribe { event ->
        handleScreenEvents(splashScreen.router, event)
      })
  }

  override fun willDetach() {
    super.willDetach()
    disposables.clear()
  }

  fun dispatchBackPress(): Boolean {
    return screenStack.back()
  }

  fun attachSplash() {
    screenStack.replace(splashScreen)
  }
}
