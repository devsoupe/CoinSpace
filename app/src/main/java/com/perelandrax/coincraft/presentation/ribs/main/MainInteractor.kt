package com.perelandrax.coincraft.presentation.ribs.main

import com.perelandrax.coincraft.presentation.ribs.navigation.NavigationInteractor
import com.perelandrax.coincraft.presentation.ribs.navigation.menustream.NavigationMenuEvent.ABOUT
import com.perelandrax.coincraft.presentation.ribs.navigation.menustream.NavigationMenuEvent.COINS
import com.perelandrax.coincraft.presentation.ribs.navigation.menustream.NavigationMenuEvent.ICO
import com.perelandrax.coincraft.presentation.ribs.navigation.menustream.NavigationMenuEventStreamUpdater
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

  @Inject
  lateinit var presenter: MainPresenter

  @Inject
  lateinit var navigationMenuEventStreamUpdater: NavigationMenuEventStreamUpdater

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    routeToToolbar()
    routeToNavigation()
    routeToNaviType()
  }

  fun routeToToolbar() {
    router.attachToolbar()
  }

  fun routeToNavigation() {
    router.attachNavigation()
  }

  fun routeToNaviType() {
    router.attachNaviType()
  }

  override fun willResignActive() {
    super.willResignActive()
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
      navigationMenuEventStreamUpdater.updateMenuEvent(ICO)
    }

    override fun aboutSelected() {
      navigationMenuEventStreamUpdater.updateMenuEvent(ABOUT)
    }
  }
}
