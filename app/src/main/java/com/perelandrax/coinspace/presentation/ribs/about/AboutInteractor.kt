package com.perelandrax.coinspace.presentation.ribs.about

import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [AboutScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class AboutInteractor : Interactor<AboutInteractor.AboutPresenter, AboutRouter>() {

  @Inject lateinit var presenter: AboutPresenter
//    @Inject lateinit var listener: Listener

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface AboutPresenter

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
