package com.perelandrax.coincraft.presentation.ribs.upcoming

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [UpcomingScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class UpcomingInteractor : Interactor<UpcomingInteractor.UpcomingPresenter, UpcomingRouter>() {

  @Inject
  lateinit var presenter: UpcomingPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface UpcomingPresenter

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
