package com.perelandrax.coinspace.presentation.ribs.coindetail

import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [CoinDetailScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class CoinDetailInteractor : Interactor<CoinDetailInteractor.CoinDetailPresenter, CoinDetailRouter>() {

  @Inject lateinit var presenter: CoinDetailPresenter

//  @Inject
//  lateinit var listener: Listener

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CoinDetailPresenter {

    fun showLoading()
    fun hideLoading()
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
