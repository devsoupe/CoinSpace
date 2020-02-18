package com.perelandrax.coinspace.presentation.ribs.about

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
 * Builder for the {@link AboutScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class AboutBuilder(dependency: ParentComponent) :
  ViewBuilder<AboutView, AboutRouter, AboutBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [AboutRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [AboutRouter].
   */
  fun build(parentViewGroup: ViewGroup): AboutRouter {
    val view = createView(parentViewGroup)
    val interactor = AboutInteractor()
    val component = DaggerAboutBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.aboutRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): AboutView? {
    return inflater.inflate(R.layout.layout_about_rib, parentViewGroup, false) as AboutView
  }

  interface ParentComponent

  @dagger.Module
  abstract class Module {

    @AboutScope
    @Binds
    internal abstract fun presenter(view: AboutView): AboutInteractor.AboutPresenter

    @dagger.Module
    companion object {

      @AboutScope
      @Provides
      @JvmStatic
      internal fun router(component: Component, view: AboutView, interactor: AboutInteractor): AboutRouter {
        return AboutRouter(view, interactor, component)
      }

      // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }
  }

  @AboutScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<AboutInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: AboutInteractor): Builder

      @BindsInstance
      fun view(view: AboutView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun aboutRouter(): AboutRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class AboutScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class AboutInternal
}
