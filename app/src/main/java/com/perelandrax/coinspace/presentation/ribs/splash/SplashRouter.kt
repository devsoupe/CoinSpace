package com.perelandrax.coinspace.presentation.ribs.splash

import com.perelandrax.coinspace.presentation.ribs.main.MainScreen
import com.perelandrax.coinspace.presentation.screenstack.ScreenStack
import com.perelandrax.coinspace.presentation.screenstack.ScreenViewRouter
import io.reactivex.disposables.CompositeDisposable

/**
 * Adds and removes children of {@link SplashBuilder.SplashScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class SplashRouter(view: SplashView, interactor: SplashInteractor, component: SplashBuilder.Component,
                   private val mainScreen: MainScreen) :
  ScreenViewRouter<SplashView, SplashInteractor, SplashBuilder.Component>(view, interactor, component) {

  private val disposable = CompositeDisposable()

  override fun willAttach() {
    super.willAttach()

    disposable.add(mainScreen.lifecycle()
      .subscribe { event ->
        handleScreenEvents(mainScreen.router, event)
      })
  }

  override fun willDetach() {
    super.willDetach()
    disposable.clear()
  }

  fun attachMain(screenStack: ScreenStack) {
    screenStack.replace(mainScreen)
  }
}
