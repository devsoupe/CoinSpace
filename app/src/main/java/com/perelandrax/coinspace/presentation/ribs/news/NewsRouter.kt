package com.perelandrax.coinspace.presentation.ribs.news

import com.perelandrax.coinspace.presentation.ribs.news.tablayout.TabLayoutView
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link NewsBuilder.NewsScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class NewsRouter(view: NewsView, interactor: NewsInteractor, component: NewsBuilder.Component,
                 private val tabLayoutViews: List<TabLayoutView>) :
  ViewRouter<NewsView, NewsInteractor, NewsBuilder.Component>(view, interactor, component) {

  override fun didLoad() {
    super.didLoad()
    attachTabLayoutViews(tabLayoutViews)
  }

  private fun attachTabLayoutViews(tabLayoutViews: List<TabLayoutView>) {
    for (tabLayoutView in tabLayoutViews) {
      attachChild(tabLayoutView.router)
    }
    interactor.presenter.setupTabLayoutViews(tabLayoutViews)
  }
}
