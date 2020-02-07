package com.perelandrax.coincraft.presentation.ribs.toolbar

import android.view.LayoutInflater
import android.view.ViewGroup
import com.perelandrax.coincraft.R
import com.perelandrax.coincraft.presentation.ribs.navigation.model.stream.NavigationMenuEventStreamSource
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Builder for the {@link ToolbarScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class ToolbarBuilder(dependency: ParentComponent) :
  ViewBuilder<ToolbarView, ToolbarRouter, ToolbarBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [ToolbarRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [ToolbarRouter].
   */
  fun build(parentViewGroup: ViewGroup): ToolbarRouter {
    val view = createView(parentViewGroup)
    val interactor = ToolbarInteractor()
    val component = DaggerToolbarBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.toolbarRouter()
  }

  override fun inflateView(
    inflater: LayoutInflater,
    parentViewGroup: ViewGroup
  ): ToolbarView? {
    return inflater.inflate(R.layout.layout_toolbar_rib, parentViewGroup, false) as ToolbarView
  }

  interface ParentComponent {
    fun navigationMenuEventStreamSource(): NavigationMenuEventStreamSource
  }

  @dagger.Module
  abstract class Module {

    @ToolbarScope
    @Binds
    internal abstract fun presenter(view: ToolbarView): ToolbarInteractor.ToolbarPresenter

    @dagger.Module
    companion object {

      @ToolbarScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: ToolbarView,
        interactor: ToolbarInteractor
      ): ToolbarRouter {
        return ToolbarRouter(view, interactor, component)
      }

      // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }
  }

  @ToolbarScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<ToolbarInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: ToolbarInteractor): Builder

      @BindsInstance
      fun view(view: ToolbarView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun toolbarRouter(): ToolbarRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class ToolbarScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class ToolbarInternal
}
