package com.perelandrax.coinspace.presentation.ribs.social

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link SocialBuilder.SocialScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class SocialRouter(view: SocialView, interactor: SocialInteractor, component: SocialBuilder.Component) :
  ViewRouter<SocialView, SocialInteractor, SocialBuilder.Component>(view, interactor, component)
