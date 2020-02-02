package com.perelandrax.coincraft.ribs.navitype

import android.annotation.SuppressLint
import com.perelandrax.coincraft.ribs.navigation.stream.NavigationMenuEvent.ABOUT
import com.perelandrax.coincraft.ribs.navigation.stream.NavigationMenuEvent.COINS
import com.perelandrax.coincraft.ribs.navigation.stream.NavigationMenuEvent.ICO
import com.perelandrax.coincraft.ribs.navigation.stream.NavigationMenuEventStreamSource
import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [NaviTypeScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class NaviTypeInteractor : Interactor<EmptyPresenter, NaviTypeRouter>() {

  @Inject lateinit var navigationMenuEventStreamSource: NavigationMenuEventStreamSource

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    handleNavigationMenuEventStreamSource()
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  @SuppressLint("CheckResult")
  private fun handleNavigationMenuEventStreamSource() {
    navigationMenuEventStreamSource.event
      .subscribe { event ->
        when (event) {
          COINS -> routeToCoins()
          ICO -> routeToICO()
          ABOUT -> routeToAbout()
        }
      }
  }

  fun routeToCoins() {
    println("COINS")
  }

  fun routeToICO() {
    println("ICO")
  }

  fun routeToAbout() {
    println("ABOUT")
  }
}
