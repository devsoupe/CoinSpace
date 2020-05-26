package com.perelandrax.coinspace.presentation.ribs.coindetail

import com.perelandrax.coinspace.data.CoinRepository
import com.perelandrax.coinspace.domain.CoinWebsite
import com.perelandrax.coinspace.domain.coindetail.CoinDetail
import com.perelandrax.coinspace.interactors.GetCoinDetail
import com.perelandrax.coinspace.presentation.coroutine.CoroutineScopeProvider
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Coordinates Business Logic for [CoinDetailScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class CoinDetailInteractor : Interactor<CoinDetailInteractor.CoinDetailPresenter, CoinDetailRouter>(),
  CoroutineScopeProvider {

  @Inject lateinit var presenter: CoinDetailPresenter
  @Inject lateinit var coinRepository: CoinRepository
  @Inject lateinit var getCoinDetail: GetCoinDetail

  private val disposables = CompositeDisposable()

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.showLoading()

    subscribePresenterEvent()
    updateCoinDetail()
  }

  private fun subscribePresenterEvent() {
    presenter.onNavigateWebsite().subscribe { routeCoinWebsite(it) }
  }

  private fun updateCoinDetail() = launch {
    runCatching { getCoinDetail.invoke() }.apply {
      dispatchUi {
        onSuccess(presenter::showCoinDetail)
        onFailure(presenter::showError)
        presenter.hideLoading()
      }
    }
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
