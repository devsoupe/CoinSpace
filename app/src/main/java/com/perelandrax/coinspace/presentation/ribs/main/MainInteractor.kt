package com.perelandrax.coinspace.presentation.ribs.main

import com.perelandrax.coinspace.presentation.ribs.navigation.NavigationInteractor
import com.perelandrax.coinspace.presentation.ribs.navigation.menustream.NavigationMenuEvent.*
import com.perelandrax.coinspace.presentation.ribs.navigation.menustream.NavigationMenuEventStreamUpdater
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MainScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class MainInteractor : Interactor<MainInteractor.MainPresenter, MainRouter>() {

  @Inject lateinit var presenter: MainPresenter
  @Inject lateinit var navigationMenuEventStreamUpdater: NavigationMenuEventStreamUpdater

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

//    routeToToolbar()
//    routeToNavigation()
//    routeToNaviType()
  }

  private fun routeToToolbar() {
    router.attachToolbar()
  }

  private fun routeToNavigation() {
    router.attachNavigation()
  }

  private fun routeToNaviType() {
    router.attachNaviType()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MainPresenter

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener

  inner class NavigationListener : NavigationInteractor.Listener {

    override fun coinsSelected() {
      navigationMenuEventStreamUpdater.updateMenuEvent(COINS)
    }

    override fun icoSelected() {
      navigationMenuEventStreamUpdater.updateMenuEvent(NEWS)
    }

    override fun aboutSelected() {
      navigationMenuEventStreamUpdater.updateMenuEvent(ABOUT)
    }
  }
}
