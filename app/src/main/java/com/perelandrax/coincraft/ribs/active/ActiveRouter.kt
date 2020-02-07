package com.perelandrax.coincraft.ribs.active

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ActiveBuilder.ActiveScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class ActiveRouter(
  view: ActiveView,
  interactor: ActiveInteractor,
  component: ActiveBuilder.Component
) : ViewRouter<ActiveView, ActiveInteractor, ActiveBuilder.Component>(view, interactor, component)
