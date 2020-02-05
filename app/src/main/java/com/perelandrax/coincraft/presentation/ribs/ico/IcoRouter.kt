package com.perelandrax.coincraft.presentation.ribs.ico

import com.perelandrax.coincraft.presentation.ribs.ico.view.tablayout.TabLayoutView
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link IcoBuilder.IcoScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class IcoRouter(
  view: IcoView,
  interactor: IcoInteractor,
  component: IcoBuilder.Component,
  private val tabLayoutViews: List<TabLayoutView>
) : ViewRouter<IcoView, IcoInteractor, IcoBuilder.Component>(view, interactor, component) {

  override fun didLoad() {
    super.didLoad()
    attachTabLayoutViews(tabLayoutViews)
  }

  private fun attachTabLayoutViews(tabLayoutViews: List<TabLayoutView>) {
    for (tabLayoutView in tabLayoutViews) {
      attachChild(tabLayoutView.router)
    }
    interactor.presenter.setupTabLayoutViews(tabLayoutViews)
  }
}
