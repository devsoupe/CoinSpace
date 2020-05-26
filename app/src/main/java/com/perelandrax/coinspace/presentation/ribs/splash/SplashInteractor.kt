package com.perelandrax.coinspace.presentation.ribs.splash

import com.perelandrax.coinspace.data.CoinRepository
import com.perelandrax.coinspace.domain.CoinMaster
import com.perelandrax.coinspace.interactors.GetCoinMaster
import com.perelandrax.coinspace.presentation.coroutine.CoroutineScopeProvider
import com.perelandrax.coinspace.presentation.ribs.splash.masterstream.CoinMasterStreamUpdater
import com.perelandrax.coinspace.presentation.screenstack.ScreenStack
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Coordinates Business Logic for [SplashScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class SplashInteractor : Interactor<SplashInteractor.SplashPresenter, SplashRouter>(),
  CoroutineScopeProvider {

  @Inject lateinit var presenter: SplashPresenter
  @Inject lateinit var screenStack: ScreenStack
  @Inject lateinit var coinRepository: CoinRepository
  @Inject lateinit var coinMasterStreamUpdater: CoinMasterStreamUpdater
  @Inject lateinit var getCoinMaster: GetCoinMaster

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.showLoading()
    getCoinMaster()
  }

  private fun getCoinMaster() = launch {
    runCatching { getCoinMaster.invoke() }.apply {
      dispatchUi {
        onSuccess(::updateCoinMasterAndRouteToMain)
        onFailure(presenter::showError)
        presenter.hideLoading()
      }
    }
  }

  private fun updateCoinMasterAndRouteToMain(coinMasters: List<CoinMaster>) {
    coinMasterStreamUpdater.updateSource(coinMasters)
    router.attachMain(screenStack)
  }

  override fun willResignActive() {
    super.willResignActive()
    parentJob.cancelChildren()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface SplashPresenter {

    fun showLoading()
    fun hideLoading()

    fun showError(throwable: Throwable)
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
