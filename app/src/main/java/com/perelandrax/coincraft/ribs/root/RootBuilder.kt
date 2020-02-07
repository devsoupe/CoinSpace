package com.perelandrax.coincraft.ribs.root

import android.view.LayoutInflater
import android.view.ViewGroup
import com.perelandrax.coincraft.R
import com.perelandrax.coincraft.ribs.main.MainBuilder
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Builder for the {@link RootScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class RootBuilder(dependency: ParentComponent) :
  ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [RootRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [RootRouter].
   */
  fun build(parentViewGroup: ViewGroup): RootRouter {
    val view = createView(parentViewGroup)
    val interactor = RootInteractor()
    val component = DaggerRootBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.rootRouter()
  }

  override fun inflateView(
    inflater: LayoutInflater,
    parentViewGroup: ViewGroup
  ): RootView? {
    return inflater.inflate(R.layout.layout_root_rib, parentViewGroup, false) as RootView
  }

  interface ParentComponent {

  }

  @dagger.Module
  abstract class Module {

    @RootScope
    @Binds
    internal abstract fun presenter(view: RootView): RootInteractor.RootPresenter

    @dagger.Module
    companion object {

      @RootScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: RootView,
        interactor: RootInteractor
      ): RootRouter {
        return RootRouter(view, interactor, component, MainBuilder(component))
      }

      // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }
  }

  @RootScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component :
    InteractorBaseComponent<RootInteractor>,
    BuilderComponent,
    MainBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: RootInteractor): Builder

      @BindsInstance
      fun view(view: RootView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun rootRouter(): RootRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class RootScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class RootInternal
}
