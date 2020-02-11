package com.perelandrax.coincraft.presentation.ribs.coins

import com.perelandrax.coincraft.domain.repository.CoinListNetworkRepository
import com.perelandrax.coincraft.data.model.mapToDomain
import com.perelandrax.coincraft.presentation.ribs.coins.coinlist.CoinListViewModel
import com.perelandrax.coincraft.utilities.Coroutines
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Coordinates Business Logic for [CoinsScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class CoinsInteractor : Interactor<CoinsInteractor.CoinsPresenter, CoinsRouter>(),
  CoroutineScope {

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + parentJob + coroutineExceptionHandler

  private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
  }

  private val parentJob = SupervisorJob()

  @Inject
  lateinit var presenter: CoinsPresenter

  @Inject
  lateinit var coinListNetworkRepository: CoinListNetworkRepository

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.showLoading()

    presenter.onRefresh()
      .subscribeBy(
        onNext = { getCoinList() },
        onError = { it.printStackTrace() })

    getCoinList()
  }

  private fun getCoinList() {
    launch {
      Coroutines.log("updateCoinListFromNetwork", coroutineContext)

      delay(2000)

      runCatching { coinListNetworkRepository.getCoinMarketCapList() }.apply {

        onSuccess {
          mutableListOf<CoinListViewModel>().apply {
            it.forEach { add(it.mapToDomain()) }
            presenter.showCoinList(this)
          }
        }

        onFailure {
          presenter.showError()
        }
      }

      presenter.hideLoading()
    }
  }

  override fun willResignActive() {
    super.willResignActive()
    parentJob.cancelChildren()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CoinsPresenter {

    fun onRefresh(): Observable<Unit>

    fun showLoading()
    fun hideLoading()

    fun showError()
    fun showCoinList(coinList: List<CoinListViewModel>)
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
