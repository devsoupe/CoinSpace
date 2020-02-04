package com.perelandrax.coincraft.presentation.ribs.ico

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [IcoScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class IcoInteractor : Interactor<IcoInteractor.IcoPresenter, IcoRouter>() {

  @Inject lateinit var presenter: IcoPresenter
//    @Inject lateinit var listener: Listener

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
  interface IcoPresenter

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
