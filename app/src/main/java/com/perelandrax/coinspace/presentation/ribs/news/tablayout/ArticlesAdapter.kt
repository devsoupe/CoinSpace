package com.perelandrax.coinspace.presentation.ribs.news.tablayout

import android.view.ViewGroup
import com.perelandrax.coinspace.presentation.ribs.article.ArticleBuilder
import com.uber.rib.core.ViewRouter

class ArticlesAdapter(val builder: ArticleBuilder, val parentViewGroup: ViewGroup) : TabLayoutViewBuilder() {

  override val title: String
    get() = "ARTICLE"

  override fun build(): ViewRouter<*, *, *> {
    return builder.build(parentViewGroup)
  }
}