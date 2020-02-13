package com.perelandrax.coinspace.presentation.ribs.about

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link AboutBuilder.AboutScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class AboutRouter(view: AboutView, interactor: AboutInteractor, component: AboutBuilder.Component
) : ViewRouter<AboutView, AboutInteractor, AboutBuilder.Component>(view, interactor, component)
