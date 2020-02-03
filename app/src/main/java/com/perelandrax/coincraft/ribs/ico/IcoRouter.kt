package com.perelandrax.coincraft.ribs.ico

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link IcoBuilder.IcoScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class IcoRouter(
  view: IcoView,
  interactor: IcoInteractor,
  component: IcoBuilder.Component
) : ViewRouter<IcoView, IcoInteractor, IcoBuilder.Component>(view, interactor, component)
