package com.perelandrax.coinspace.presentation.ribs.article

import android.view.LayoutInflater
import android.view.ViewGroup
import com.perelandrax.coinspace.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Builder for the {@link ArticleScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class ArticleBuilder(dependency: ParentComponent) :
  ViewBuilder<ArticleView, ArticleRouter, ArticleBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [ArticleRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [ArticleRouter].
   */
  fun build(parentViewGroup: ViewGroup): ArticleRouter {
    val view = createView(parentViewGroup)
    val interactor = ArticleInteractor()
    val component = DaggerArticleBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.articleRouter()
  }

  override fun inflateView(
    inflater: LayoutInflater,
    parentViewGroup: ViewGroup
  ): ArticleView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return inflater.inflate(R.layout.layout_article_rib, parentViewGroup, false) as ArticleView
  }

  interface ParentComponent {

  }

  @dagger.Module
  abstract class Module {

    @ArticleScope
    @Binds
    internal abstract fun presenter(view: ArticleView): ArticleInteractor.ArticlePresenter

    @dagger.Module
    companion object {

      @ArticleScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: ArticleView,
        interactor: ArticleInteractor
      ): ArticleRouter {
        return ArticleRouter(view, interactor, component)
      }

      // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }
  }

  @ArticleScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<ArticleInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: ArticleInteractor): Builder

      @BindsInstance
      fun view(view: ArticleView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun articleRouter(): ArticleRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class ArticleScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class ArticleInternal
}
