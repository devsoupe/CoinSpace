package com.perelandrax.coincraft.presentation.ribs.splash

import com.perelandrax.coincraft.utilities.Coroutines
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

  @Inject
  lateinit var presenter: SplashPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    routeToMain()
  }

  fun routeToMain() {
    launch {
      Coroutines.log("routeToMain", coroutineContext)

      delay(2000)

      router.attachMain()
    }
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
  interface SplashPresenter

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
