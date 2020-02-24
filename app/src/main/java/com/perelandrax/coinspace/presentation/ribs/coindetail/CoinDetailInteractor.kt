package com.perelandrax.coinspace.presentation.ribs.coindetail

import com.perelandrax.coinspace.data.CoinRepository
import com.perelandrax.coinspace.domain.CoinWebsite
import com.perelandrax.coinspace.domain.coindetail.CoinDetail
import com.perelandrax.coinspace.utilities.Coroutines
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*
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
  @Inject lateinit var coidId: String

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    subscribePresenterEvent()
    updateCoinDetail()
  }

  private fun subscribePresenterEvent() {
    presenter.onNavigateWebsite().subscribe { routeCoinWebsite(it) }
  }

  private fun updateCoinDetail() = launch {
    Coroutines.log("updateCoinDetail", coroutineContext)

    presenter.showLoading()

    val delay = async { delay(1000) }

    runCatching { coinRepository.getCoinDetail(this, coidId) }.apply {
      delay.await()

      onSuccess(presenter::showCoinDetail)
      onFailure(presenter::showError)
    }

    presenter.hideLoading()
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
