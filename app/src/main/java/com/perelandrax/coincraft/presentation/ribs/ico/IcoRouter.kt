package com.perelandrax.coincraft.presentation.ribs.ico

import com.perelandrax.coincraft.presentation.ribs.active.ActiveBuilder
import com.perelandrax.coincraft.presentation.ribs.active.ActiveRouter
import com.perelandrax.coincraft.presentation.ribs.upcoming.UpcomingBuilder
import com.perelandrax.coincraft.presentation.ribs.upcoming.UpcomingRouter
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
  private val activeBuilder: ActiveBuilder,
  private val upcomingBuilder: UpcomingBuilder
) : ViewRouter<IcoView, IcoInteractor, IcoBuilder.Component>(view, interactor, component) {

  override fun didLoad() {
    super.didLoad()

    val activeRouter = activeBuilder.build(view)
    val upcomingRouter = upcomingBuilder.build(view)

    attachActive(activeRouter)
    attachUpcoming(upcomingRouter)

    interactor.presenter.setupTabLayoutAndViewPager(activeRouter.view, upcomingRouter.view)
  }

  fun attachActive(activeRouter: ActiveRouter) {
    attachChild(activeRouter)
  }

  fun attachUpcoming(upcomingRouter: UpcomingRouter) {
    attachChild(upcomingRouter)
  }
}
