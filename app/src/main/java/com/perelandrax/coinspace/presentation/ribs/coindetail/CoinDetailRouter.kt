package com.perelandrax.coinspace.presentation.ribs.coindetail

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link CoinDetailBuilder.CoinDetailScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class CoinDetailRouter(
  view: CoinDetailView,
  interactor: CoinDetailInteractor,
  component: CoinDetailBuilder.Component
) : ViewRouter<CoinDetailView, CoinDetailInteractor, CoinDetailBuilder.Component>(view, interactor, component)
