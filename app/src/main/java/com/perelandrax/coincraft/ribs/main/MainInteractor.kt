package com.perelandrax.coincraft.ribs.main

import com.perelandrax.coincraft.ribs.navigation.NavigationInteractor
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

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    routeToMainBottomTab()
  }

  fun routeToMainBottomTab() {
    router.attachMainBottomTab()
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
      println("coinsSelected")
    }

    override fun icoSelected() {
      println("icoSelected")
    }

    override fun aboutSelected() {
      println("aboutSelected")
    }
  }
}
