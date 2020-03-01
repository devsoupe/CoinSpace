package com.perelandrax.coinspace.presentation.ribs.coinwebsite

import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.domain.CoinWebsite
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [CoinWebsiteScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class CoinWebsiteInteractor : Interactor<CoinWebsiteInteractor.CoinWebsitePresenter, CoinWebsiteRouter>() {

  @Inject lateinit var presenter: CoinWebsitePresenter
  @Inject lateinit var coinWebsite: CoinWebsite
//  @Inject lateinit var listener: Listener

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.updateTitle(coinWebsite?.title ?: "")
    presenter.showWebsite(coinWebsite?.url ?: "")
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CoinWebsitePresenter {

    fun updateTitle(title: String)
    fun showWebsite(url: String)
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
