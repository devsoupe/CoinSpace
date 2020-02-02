package com.perelandrax.coincraft.ribs.navitype

import com.uber.rib.core.Router

/**
 * Adds and removes children of {@link NaviTypeBuilder.NaviTypeScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class NaviTypeRouter(
  interactor: NaviTypeInteractor, component: NaviTypeBuilder.Component
) : Router<NaviTypeInteractor, NaviTypeBuilder.Component>(interactor, component)
