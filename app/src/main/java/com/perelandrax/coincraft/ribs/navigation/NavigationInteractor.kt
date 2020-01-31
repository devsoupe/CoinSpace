package com.perelandrax.coincraft.ribs.navigation

import com.perelandrax.coincraft.ribs.navigation.MenuEvent.ABOUT
import com.perelandrax.coincraft.ribs.navigation.MenuEvent.COINS
import com.perelandrax.coincraft.ribs.navigation.MenuEvent.ICO
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MainBottomTabScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class NavigationInteractor : Interactor<NavigationInteractor.MainBottomTabPresenter, NavigationRouter>() {

  @Inject lateinit var presenter: MainBottomTabPresenter
  @Inject lateinit var listener: Listener

  private val disposables = CompositeDisposable()

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.menuEvents()
      .doOnError { OnErrorNotImplementedException(it) }
      .subscribe { event ->
        when (event) {
          COINS -> listener.coinsSelected()
          ICO -> listener.icoSelected()
          ABOUT -> listener.aboutSelected()
        }
      }
  }

  override fun willResignActive() {
    super.willResignActive()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MainBottomTabPresenter {
    fun menuEvents(): Observable<MenuEvent>
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener {
    fun coinsSelected()
    fun icoSelected()
    fun aboutSelected()
  }
}
