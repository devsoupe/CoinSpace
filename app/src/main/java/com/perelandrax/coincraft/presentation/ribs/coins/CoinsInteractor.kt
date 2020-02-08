package com.perelandrax.coincraft.presentation.ribs.coins

import com.perelandrax.coincraft.data.repository.CoinListNetworkRepository
import com.perelandrax.coincraft.data.repository.remote.model.mapToDomain
import com.perelandrax.coincraft.presentation.ribs.coins.model.CoinListViewModel
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Coordinates Business Logic for [CoinsScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class CoinsInteractor : Interactor<CoinsInteractor.CoinsPresenter, CoinsRouter>() {

  @Inject
  lateinit var presenter: CoinsPresenter

  @Inject
  lateinit var coinListNetworkRepository: CoinListNetworkRepository

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    updateCoinListFromNetwork()

    presenter.onRefresh()
      .subscribeBy(onNext = {
        updateCoinListFromNetwork()
      }, onError = {

      })
  }

  private fun updateCoinListFromNetwork() {
    CoroutineScope(Dispatchers.Main).launch {
      presenter.showLoading()

      val coinList = async { coinListNetworkRepository.getCoinListCoinMarketCap() }.await()
      val coinListViewModels = mutableListOf<CoinListViewModel>().apply {
        for (coin in coinList) {
          add(coin.mapToDomain())
        }
      }

      presenter.showCoinList(coinListViewModels)
      presenter.hideLoading()
    }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CoinsPresenter {

    fun onRefresh(): Observable<Unit>

    fun showLoading()
    fun hideLoading()
    fun showCoinList(coinList: List<CoinListViewModel>)
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
