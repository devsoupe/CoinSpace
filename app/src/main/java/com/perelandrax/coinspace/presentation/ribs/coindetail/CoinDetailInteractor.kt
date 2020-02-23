package com.perelandrax.coinspace.presentation.ribs.coindetail

import com.perelandrax.coinspace.data.CoinRepository
import com.perelandrax.coinspace.domain.Coin
import com.perelandrax.coinspace.domain.CoinWebsite
import com.perelandrax.coinspace.domain.coindetail.CoinDetail
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Coordinates Business Logic for [CoinDetailScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class CoinDetailInteractor : Interactor<CoinDetailInteractor.CoinDetailPresenter, CoinDetailRouter>(),
  CoroutineScope {

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + parentJob + coroutineExceptionHandler

  private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
  }

  private val parentJob = SupervisorJob()
  private val disposables = CompositeDisposable()

  @Inject lateinit var presenter: CoinDetailPresenter
  @Inject lateinit var coinRepository: CoinRepository
  @Inject lateinit var coinDetail: CoinDetail

//  @Inject lateinit var listener: Listener

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    updateCoinDetail()
    handlePresenterEvent()
  }

  private fun updateCoinDetail() {
    presenter.showCoinDetail(coinDetail)
  }

  private fun handlePresenterEvent() {
    presenter.onNavigateWebsite().subscribe { routeCoinWebsite(it) }
  }

  private fun routeCoinWebsite(coinWebsite: CoinWebsite) {
    router.attachCoinWebsite(coinWebsite)
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CoinDetailPresenter {

    fun onNavigateWebsite(): Observable<CoinWebsite>

    fun showLoading()
    fun hideLoading()

    fun showError(throwable: Throwable)
    fun showCoinDetail(coinDetail: CoinDetail)
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
