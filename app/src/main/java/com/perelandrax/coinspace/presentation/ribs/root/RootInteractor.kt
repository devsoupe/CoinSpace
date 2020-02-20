package com.perelandrax.coinspace.presentation.ribs.root

import com.orhanobut.logger.Logger
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

  @Inject lateinit var presenter: RootPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    Logger.t("RIBS").i("didBecomeActive")

    routeToSplash()
  }

  override fun willResignActive() {
    super.willResignActive()
    Logger.t("RIBS").i("willResignActive")
  }

  override fun handleBackPress(): Boolean {
    return router.dispatchBackPress()
  }

  fun routeToSplash() {
    router.attachSplash()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface RootPresenter

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
