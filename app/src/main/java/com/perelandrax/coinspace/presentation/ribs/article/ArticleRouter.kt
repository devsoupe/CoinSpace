package com.perelandrax.coinspace.presentation.ribs.article

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ArticleBuilder.ArticleScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class ArticleRouter(view: ArticleView, interactor: ArticleInteractor, component: ArticleBuilder.Component) :
  ViewRouter<ArticleView, ArticleInteractor, ArticleBuilder.Component>(view, interactor, component)
