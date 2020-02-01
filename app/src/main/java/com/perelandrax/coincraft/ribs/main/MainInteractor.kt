package com.perelandrax.coincraft.ribs.main

import com.perelandrax.coincraft.ribs.navigation.NavigationInteractor
import com.perelandrax.coincraft.ribs.navigation.stream.NavigationMenuEvent.ABOUT
import com.perelandrax.coincraft.ribs.navigation.stream.NavigationMenuEvent.COINS
import com.perelandrax.coincraft.ribs.navigation.stream.NavigationMenuEvent.ICO
import com.perelandrax.coincraft.ribs.navigation.stream.NavigationMenuEventStreamSource
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
  @Inject lateinit var navigationMenuEventStreamSource: NavigationMenuEventStreamSource

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    routeToNavigation()

    handleNavigationMenuEventStreamSource()
  }

  private fun handleNavigationMenuEventStreamSource() {
    navigationMenuEventStreamSource.event.subscribe { event ->
      when (event) {
        COINS -> {
          println("COINS")
        }

        ICO -> {
          println("ICO")
        }

        ABOUT -> {
          println("ABOUT")
        }
      }
    }
  }

  fun routeToNavigation() {
    router.attachNavigation()
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
}
