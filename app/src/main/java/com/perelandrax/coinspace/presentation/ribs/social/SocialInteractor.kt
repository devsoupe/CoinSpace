package com.perelandrax.coinspace.presentation.ribs.social

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [SocialScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class SocialInteractor : Interactor<SocialInteractor.SocialPresenter, SocialRouter>() {

  @Inject
  lateinit var presenter: SocialPresenter

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface SocialPresenter

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
