package com.perelandrax.coincraft.presentation.ribs.toolbar

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ToolbarBuilder.ToolbarScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class ToolbarRouter(view: ToolbarView, interactor: ToolbarInteractor, component: ToolbarBuilder.Component) :
  ViewRouter<ToolbarView, ToolbarInteractor, ToolbarBuilder.Component>(view, interactor, component)
