package com.perelandrax.coincraft.presentation.ribs.navitype

import android.annotation.SuppressLint
import com.perelandrax.coincraft.presentation.ribs.navigation.model.stream.NavigationMenuEvent.*
import com.perelandrax.coincraft.presentation.ribs.navigation.model.stream.NavigationMenuEventStreamSource
import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Coordinates Business Logic for [NaviTypeScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class NaviTypeInteractor : Interactor<EmptyPresenter, NaviTypeRouter>() {

  @Inject
  lateinit var navigationMenuEventStreamSource: NavigationMenuEventStreamSource

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
      .subscribeBy(onNext = { event ->
        when (event) {
          COINS -> routeToCoins()
          ICO -> routeToICO()
          ABOUT -> routeToAbout()
        }
      }, onError = {
        it.printStackTrace()
      })
  }

  fun routeToCoins() {
    router.detachIco()
    router.detachAbout()
    router.attachCoins()
  }

  fun routeToICO() {
    router.detachCoins()
    router.detachAbout()
    router.attachIco()
  }

  fun routeToAbout() {
    router.detachCoins()
    router.detachIco()
    router.attachAbout()
  }
}
