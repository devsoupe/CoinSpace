package com.perelandrax.coincraft.ribs.main

import com.perelandrax.coincraft.ribs.navigation.NavigationInteractor
import com.perelandrax.coincraft.ribs.navigation.model.stream.NavigationMenuEvent.ABOUT
import com.perelandrax.coincraft.ribs.navigation.model.stream.NavigationMenuEvent.COINS
import com.perelandrax.coincraft.ribs.navigation.model.stream.NavigationMenuEvent.ICO
import com.perelandrax.coincraft.ribs.navigation.model.stream.NavigationMenuEventStreamUpdater
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
      navigationMenuEventStreamUpdater.updateMenuId(COINS)
    }

    override fun icoSelected() {
      navigationMenuEventStreamUpdater.updateMenuId(ICO)
    }

    override fun aboutSelected() {
      navigationMenuEventStreamUpdater.updateMenuId(ABOUT)
    }
  }
}
