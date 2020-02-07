package com.perelandrax.coincraft.ribs.toolbar

import android.annotation.SuppressLint
import com.perelandrax.coincraft.ribs.navigation.model.stream.NavigationMenuEvent
import com.perelandrax.coincraft.ribs.navigation.model.stream.NavigationMenuEvent.*
import com.perelandrax.coincraft.ribs.navigation.model.stream.NavigationMenuEventStreamSource
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Coordinates Business Logic for [ToolbarScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class ToolbarInteractor : Interactor<ToolbarInteractor.ToolbarPresenter, ToolbarRouter>() {

  @Inject
  lateinit var presenter: ToolbarPresenter
  @Inject
  lateinit var navigationMenuEventStreamSource: NavigationMenuEventStreamSource

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    handleNavigationMenuEventStreamSource()
  }

  override fun willResignActive() {
    super.willResignActive()
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
    navigationMenuEventStreamSource.event
      .subscribeBy(onNext = { event ->
        presenter.updateTitle(TitleForNavigationMenuEvent.getTitle(event))
      }, onError = {
        it.printStackTrace()
      })
  }

  enum class TitleForNavigationMenuEvent(
    val event: NavigationMenuEvent,
    val title: String
  ) {

    COINS_TITLE(COINS, "Coin Craft"),
    ICO_TITLE(ICO, "ICO List"),
    ABOUT_TITLE(ABOUT, "About");

    companion object {

      fun getTitle(event: NavigationMenuEvent): String {
        return when (event) {
          ICO_TITLE.event -> ICO_TITLE.title
          ABOUT_TITLE.event -> ABOUT_TITLE.title
          else -> COINS_TITLE.title
        }
      }
    }
  }
}
