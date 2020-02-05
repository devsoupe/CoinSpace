package com.perelandrax.coincraft.presentation.ribs.ico

import com.perelandrax.coincraft.presentation.ribs.ico.view.tablayout.TabLayoutView
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

  @Inject
  lateinit var presenter: IcoPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
  }

  override fun willResignActive() {
    super.willResignActive()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface IcoPresenter {
    fun setupTabLayoutViews(tabLayoutViews: List<TabLayoutView>)
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
