package com.perelandrax.coinspace.presentation.ribs.coins

import com.orhanobut.logger.Logger
import com.perelandrax.coinspace.data.CoinRepository
import com.perelandrax.coinspace.domain.Coin
import com.perelandrax.coinspace.domain.CoinMaster
import com.perelandrax.coinspace.presentation.ribs.splash.masterstream.CoinMasterStreamSource
import com.perelandrax.coinspace.utilities.Coroutines
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java8.util.stream.StreamSupport
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
  private val disposables = CompositeDisposable()

  @Inject lateinit var presenter: CoinsPresenter
  @Inject lateinit var coinRepository: CoinRepository
  @Inject lateinit var coinMasterStreamSource: CoinMasterStreamSource

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    Logger.i("didBecomeActive")

    presenter.showLoading()

    disposables.add(presenter.onRefresh().subscribeBy { updateCoinList() })
    disposables.add(presenter.onSelectCoin().subscribeBy { coin -> coin?.detailId?.let { routeCoinDetail(it) } })

    updateCoinList()
  }

  private fun updateCoinList() = launch {
    Coroutines.log("updateCoinList", coroutineContext)

    runCatching { coinRepository.getCoins() }.apply {
      onSuccess { presenter.showCoinList(mergedCoinListByDetailId(it)) }
      onFailure(presenter::showError)
    }

    presenter.hideLoading()
  }

  private fun mergedCoinListByDetailId(coinList: List<Coin>): List<Coin> {
    val coinMasterList = coinMasterStreamSource.source.value

    coinList.forEach { coin ->
      val detailId = StreamSupport.stream(coinMasterList)
        .filter { coinMaster -> coin.name == coinMaster.name }
        .findFirst()
        .orElse(CoinMaster()).id

      coin.detailId = detailId
    }

    return coinList
  }

  override fun willResignActive() {
    super.willResignActive()
    Logger.i("willResignActive")

    parentJob.cancelChildren()
    disposables.clear()
  }

  private fun routeCoinDetail(coinId: String) {
    router.attachCoinDetail(coinId)
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CoinsPresenter {

    fun onRefresh(): Observable<Unit>
    fun onSelectCoin(): Observable<Coin>

    fun showLoading()
    fun hideLoading()

    fun showError(throwable: Throwable)
    fun showCoinList(coinList: List<Coin>)
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
