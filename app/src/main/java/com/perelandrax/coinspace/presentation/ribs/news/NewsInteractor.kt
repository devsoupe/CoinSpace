package com.perelandrax.coinspace.presentation.ribs.news

import com.perelandrax.coinspace.presentation.ribs.news.tablayout.TabLayoutView
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [NewsScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class NewsInteractor : Interactor<NewsInteractor.NewsPresenter, NewsRouter>() {

  @Inject
  lateinit var presenter: NewsPresenter

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface NewsPresenter {
    fun setupTabLayoutViews(tabLayoutViews: List<TabLayoutView>)
  }

  /**
   * Listener interface implemented by a parent RIB's interactor's inner class.
   */
  interface Listener
}
