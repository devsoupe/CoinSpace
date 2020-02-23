package com.perelandrax.coinspace.presentation.ribs.coinwebsite

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link CoinWebsiteBuilder.CoinWebsiteScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class CoinWebsiteRouter(view: CoinWebsiteView, interactor: CoinWebsiteInteractor, component: CoinWebsiteBuilder.Component) :
  ViewRouter<CoinWebsiteView, CoinWebsiteInteractor, CoinWebsiteBuilder.Component>(view, interactor, component)
