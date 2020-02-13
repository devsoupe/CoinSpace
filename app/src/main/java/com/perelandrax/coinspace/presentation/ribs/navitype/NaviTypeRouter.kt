package com.perelandrax.coinspace.presentation.ribs.navitype

import android.view.ViewGroup
import com.perelandrax.coinspace.presentation.ribs.about.AboutBuilder
import com.perelandrax.coinspace.presentation.ribs.about.AboutRouter
import com.perelandrax.coinspace.presentation.ribs.coins.CoinsBuilder
import com.perelandrax.coinspace.presentation.ribs.coins.CoinsRouter
import com.perelandrax.coinspace.presentation.ribs.news.NewsBuilder
import com.perelandrax.coinspace.presentation.ribs.news.NewsRouter
import com.uber.rib.core.Router
import kotlinx.android.synthetic.main.layout_main_rib.view.*

/**
 * Adds and removes children of {@link NaviTypeBuilder.NaviTypeScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class NaviTypeRouter(interactor: NaviTypeInteractor, component: NaviTypeBuilder.Component, private val parentView: ViewGroup,
                     coinsBuilder: CoinsBuilder,
                     newsBuilder: NewsBuilder,
                     aboutBuilder: AboutBuilder) :
  Router<NaviTypeInteractor, NaviTypeBuilder.Component>(interactor, component) {

  private var coinsRouter: CoinsRouter = coinsBuilder.build(parentView)
  private var newsRouter: NewsRouter = newsBuilder.build(parentView)
  private var aboutRouter: AboutRouter = aboutBuilder.build(parentView)

  fun attachCoins() {
    attachChild(coinsRouter)
    parentView.content.addView(coinsRouter.view)
  }

  fun detachCoins() {
    detachChild(coinsRouter)
    parentView.content.removeView(coinsRouter.view)
  }

  fun attachIco() {
    attachChild(newsRouter)
    parentView.content.addView(newsRouter.view)
  }

  fun detachIco() {
    detachChild(newsRouter)
    parentView.content.removeView(newsRouter.view)
  }

  fun attachAbout() {
    attachChild(aboutRouter)
    parentView.content.addView(aboutRouter.view)
  }

  fun detachAbout() {
    detachChild(aboutRouter)
    parentView.content.removeView(aboutRouter.view)
  }
}
