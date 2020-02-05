package com.perelandrax.coincraft.presentation.ribs.upcoming

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link UpcomingBuilder.UpcomingScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class UpcomingRouter(
  view: UpcomingView,
  interactor: UpcomingInteractor,
  component: UpcomingBuilder.Component
) : ViewRouter<UpcomingView, UpcomingInteractor, UpcomingBuilder.Component>(view, interactor, component)
