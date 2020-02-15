package com.perelandrax.coinspace.presentation.ribs.toolbar

import android.annotation.SuppressLint
import com.perelandrax.coinspace.presentation.ribs.navigation.menustream.NavigationMenuEvent
import com.perelandrax.coinspace.presentation.ribs.navigation.menustream.NavigationMenuEvent.*
import com.perelandrax.coinspace.presentation.ribs.navigation.menustream.NavigationMenuEventStreamSource
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Coordinates Business Logic for [ToolbarScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class ToolbarInteractor : Interactor<ToolbarInteractor.ToolbarPresenter, ToolbarRouter>() {

  private val disposables = CompositeDisposable()

  @Inject lateinit var presenter: ToolbarPresenter
  @Inject lateinit var navigationMenuEventStreamSource: NavigationMenuEventStreamSource

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    handleNavigationMenuEventStreamSource()
  }

  override fun willResignActive() {
    super.willResignActive()
    disposables.clear()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface ToolbarPresenter {
    fun updateTitle(title: String)
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener

  @SuppressLint("CheckResult")
  private fun handleNavigationMenuEventStreamSource() {
    disposables.add(navigationMenuEventStreamSource.event
      .subscribeBy { event ->
        presenter.updateTitle(TitleForNavigationMenuEvent.getTitle(event))
      })
  }

  enum class TitleForNavigationMenuEvent(val event: NavigationMenuEvent, val title: String) {

    COINS_TITLE(COINS, "Coin Space"),
    NEWS_TITLE(NEWS, "News"),
    ABOUT_TITLE(ABOUT, "About");

    companion object {

      fun getTitle(event: NavigationMenuEvent): String {
        return when (event) {
          NEWS_TITLE.event -> NEWS_TITLE.title
          ABOUT_TITLE.event -> ABOUT_TITLE.title
          else -> COINS_TITLE.title
        }
      }
    }
  }
}
