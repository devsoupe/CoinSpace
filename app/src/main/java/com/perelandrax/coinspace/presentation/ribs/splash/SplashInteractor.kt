package com.perelandrax.coinspace.presentation.ribs.splash

import com.orhanobut.logger.Logger
import com.perelandrax.coinspace.data.CoinRepository
import com.perelandrax.coinspace.presentation.ribs.splash.masterstream.CoinMasterStreamUpdater
import com.perelandrax.coinspace.presentation.ribslib.ScreenStack
import com.perelandrax.coinspace.utilities.Coroutines
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Coordinates Business Logic for [SplashScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class SplashInteractor : Interactor<SplashInteractor.SplashPresenter, SplashRouter>(),
  CoroutineScope {

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + parentJob + coroutineExceptionHandler

  private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
  }

  private val parentJob = SupervisorJob()

  @Inject lateinit var presenter: SplashPresenter
  @Inject lateinit var screenStack: ScreenStack
  @Inject lateinit var coinRepository: CoinRepository
  @Inject lateinit var coinMasterStreamUpdater: CoinMasterStreamUpdater

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    Logger.t("RIBS").i("didBecomeActive")

    presenter.showLoading()
    getCoinMaster()
  }

  private fun getCoinMaster() {
    launch {
      Coroutines.log("getCoinMasterData", coroutineContext)

      val delay = async { delay(2000) }

      runCatching { coinRepository.getCoinMaster() }.apply {

        delay.await()

        onSuccess {
          coinMasterStreamUpdater.updateSource(it)
          routeToMain()
        }

        onFailure {
          presenter.showError()
        }
      }

//      presenter.hideLoading()
    }
  }

  private fun routeToMain() {
    router.attachMain(screenStack)
  }

  override fun willResignActive() {
    super.willResignActive()
    Logger.t("RIBS").i("willResignActive")

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

    fun showError()
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
