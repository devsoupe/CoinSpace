package com.perelandrax.coincraft.ribs.coins

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link CoinsBuilder.CoinsScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class CoinsRouter(
  view: CoinsView,
  interactor: CoinsInteractor,
  component: CoinsBuilder.Component
) : ViewRouter<CoinsView, CoinsInteractor, CoinsBuilder.Component>(view, interactor, component)
