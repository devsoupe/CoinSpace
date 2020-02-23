package com.perelandrax.coinspace.presentation.ribs.coindetail

import com.orhanobut.logger.Logger
import com.perelandrax.coinspace.data.CoinRepository
import com.perelandrax.coinspace.domain.CoinMaster
import com.perelandrax.coinspace.domain.coindetail.CoinDetail
import com.perelandrax.coinspace.utilities.Coroutines
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java8.util.stream.StreamSupport
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
  @Inject lateinit var coinDetail: CoinDetail

//  @Inject lateinit var listener: Listener

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    updateCoinDetail()
  }

  private fun updateCoinDetail() {
    presenter.showCoinDetail(coinDetail)
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CoinDetailPresenter {

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
