package com.perelandrax.coinspace.presentation.ribs.social

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
 * Builder for the {@link SocialScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class SocialBuilder(dependency: ParentComponent) :
  ViewBuilder<SocialView, SocialRouter, SocialBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [SocialRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [SocialRouter].
   */
  fun build(parentViewGroup: ViewGroup): SocialRouter {
    val view = createView(parentViewGroup)
    val interactor = SocialInteractor()
    val component = DaggerSocialBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.socialRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): SocialView? {
    return inflater.inflate(R.layout.layout_social_rib, parentViewGroup, false) as SocialView
  }

  interface ParentComponent

  @dagger.Module
  abstract class Module {

    @SocialScope
    @Binds
    internal abstract fun presenter(view: SocialView): SocialInteractor.SocialPresenter

    @dagger.Module
    companion object {

      @SocialScope
      @Provides
      @JvmStatic
      internal fun router(component: Component, view: SocialView, interactor: SocialInteractor): SocialRouter {
        return SocialRouter(view, interactor, component)
      }

      // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }
  }

  @SocialScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<SocialInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: SocialInteractor): Builder

      @BindsInstance
      fun view(view: SocialView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun socialRouter(): SocialRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class SocialScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class SocialInternal
}
