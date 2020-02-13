package com.perelandrax.coinspace.presentation.ribs.news

import android.view.LayoutInflater
import android.view.ViewGroup
import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.presentation.ribs.article.ArticleBuilder
import com.perelandrax.coinspace.presentation.ribs.news.tablayout.ArticlesAdapter
import com.perelandrax.coinspace.presentation.ribs.news.tablayout.SocialAdapter
import com.perelandrax.coinspace.presentation.ribs.social.SocialBuilder
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Builder for the {@link NewsScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */

class NewsBuilder(dependency: ParentComponent) :
  ViewBuilder<NewsView, NewsRouter, NewsBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [NewsRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [NewsRouter].
   */
  fun build(parentViewGroup: ViewGroup): NewsRouter {
    val view = createView(parentViewGroup)
    val interactor = NewsInteractor()
    val component = DaggerNewsBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.newsRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): NewsView? {
    return inflater.inflate(R.layout.layout_news_rib, parentViewGroup, false) as NewsView
  }

  interface ParentComponent {

  }

  @dagger.Module
  abstract class Module {

    @NewsScope
    @Binds
    internal abstract fun presenter(view: NewsView): NewsInteractor.NewsPresenter

    @dagger.Module
    companion object {

      @NewsScope
      @Provides
      @JvmStatic
      internal fun router(component: Component, view: NewsView, interactor: NewsInteractor): NewsRouter {
        return NewsRouter(
          view, interactor, component,
          listOf(
            ArticlesAdapter(ArticleBuilder(component), view),
            SocialAdapter(SocialBuilder(component), view)
          )
        )
      }
    }
  }

  @NewsScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<NewsInteractor>, BuilderComponent,
    ArticleBuilder.ParentComponent,
    SocialBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: NewsInteractor): Builder

      @BindsInstance
      fun view(view: NewsView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun newsRouter(): NewsRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class NewsScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class NewsInternal
}
