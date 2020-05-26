package com.perelandrax.coinspace.presentation.ribs.coins

import com.perelandrax.coinspace.presentation.ribs.coindetail.CoinDetailScreen
import com.perelandrax.coinspace.presentation.screenstack.ScreenStack
import com.perelandrax.coinspace.presentation.screenstack.ScreenViewRouter
import io.reactivex.disposables.CompositeDisposable

/**
 * Adds and removes children of {@link CoinsBuilder.CoinsScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class CoinsRouter(view: CoinsView, interactor: CoinsInteractor, component: CoinsBuilder.Component,
                  private val screenStack: ScreenStack,
                  private val coinDetailScreen: CoinDetailScreen) :
  ScreenViewRouter<CoinsView, CoinsInteractor, CoinsBuilder.Component>(view, interactor, component) {

  private val disposables = CompositeDisposable()

  override fun willAttach() {
    super.willAttach()

    disposables.add(coinDetailScreen.lifecycle()
      .subscribe { event ->
        handleScreenEvents(coinDetailScreen.router, event)
      })
  }

  override fun willDetach() {
    super.willDetach()
    disposables.clear()
  }

  fun attachCoinDetail(coinId: String) {
    coinDetailScreen.coinId = coinId
    screenStack.push(coinDetailScreen)
  }
}
