package com.perelandrax.coinspace.presentation.ribs.navitype

import android.annotation.SuppressLint
import com.perelandrax.coinspace.presentation.ribs.navigation.menustream.NavigationMenuEvent.*
import com.perelandrax.coinspace.presentation.ribs.navigation.menustream.NavigationMenuEventStreamSource
import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Coordinates Business Logic for [NaviTypeScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class NaviTypeInteractor : Interactor<EmptyPresenter, NaviTypeRouter>() {

  private val disposables = CompositeDisposable()

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
    disposables.add(navigationMenuEventStreamSource.event
      .subscribeBy { event ->
        when (event) {
          COINS -> routeToCoins()
          NEWS -> routeToNews()
          ABOUT -> routeToAbout()
        }
      })
  }

  private fun routeToCoins() {
    router.detachIco()
    router.detachAbout()
    router.attachCoins()
  }

  private fun routeToNews() {
    router.detachCoins()
    router.detachAbout()
    router.attachIco()
  }

  private fun routeToAbout() {
    router.detachCoins()
    router.detachIco()
    router.attachAbout()
  }
}
