package com.perelandrax.coinspace.presentation.ribs.coindetail

import com.perelandrax.coinspace.presentation.ribs.coinwebsite.CoinWebsiteScreen
import com.perelandrax.coinspace.presentation.ribslib.ScreenStack
import com.perelandrax.coinspace.presentation.ribslib.ScreenViewRouter
import io.reactivex.disposables.CompositeDisposable

/**
 * Adds and removes children of {@link CoinDetailBuilder.CoinDetailScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class CoinDetailRouter(view: CoinDetailView, interactor: CoinDetailInteractor, component: CoinDetailBuilder.Component,
                       private val screenStack: ScreenStack,
                       private val coinWebsiteScreen: CoinWebsiteScreen) :
  ScreenViewRouter<CoinDetailView, CoinDetailInteractor, CoinDetailBuilder.Component>(view, interactor, component) {

  private val disposables = CompositeDisposable()

  override fun willAttach() {
    super.willAttach()

    disposables.add(coinWebsiteScreen.lifecycle()
      .subscribe { event ->
        handleScreenEvents(coinWebsiteScreen.router, event)
      })
  }

  override fun willDetach() {
    super.willDetach()
    disposables.clear()
  }

  fun attachCoinWebsite() {
    screenStack.present(coinWebsiteScreen)
  }
}