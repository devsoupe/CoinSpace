package com.perelandrax.coincraft.ribs.mainBottomTab

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link MainBottomTabBuilder.MainBottomTabScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class MainBottomTabRouter(
        view: MainBottomTabView,
        interactor: MainBottomTabInteractor,
        component: MainBottomTabBuilder.Component
) : ViewRouter<MainBottomTabView, MainBottomTabInteractor, MainBottomTabBuilder.Component>(view, interactor, component)
