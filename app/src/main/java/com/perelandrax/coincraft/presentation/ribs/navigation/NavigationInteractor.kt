package com.perelandrax.coincraft.presentation.ribs.navigation

import com.perelandrax.coincraft.R
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MainBottomTabScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class NavigationInteractor : Interactor<NavigationInteractor.MainBottomTabPresenter, NavigationRouter>() {

  @Inject
  lateinit var presenter: MainBottomTabPresenter

  @Inject
  lateinit var listener: Listener

  private val disposables = CompositeDisposable()

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.menuIdEvent()
      .subscribeBy(onNext = { menuId ->
        when (menuId) {
          R.id.coins -> listener.coinsSelected()
          R.id.ico -> listener.icoSelected()
          R.id.about -> listener.aboutSelected()
        }
      }, onError = {

      })
  }

  override fun willResignActive() {
    super.willResignActive()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MainBottomTabPresenter {
    fun menuIdEvent(): Observable<Int>
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
