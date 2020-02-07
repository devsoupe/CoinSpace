package com.perelandrax.coincraft.ribs.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.perelandrax.coincraft.R
import com.perelandrax.coincraft.ribs.navigation.NavigationBuilder
import com.perelandrax.coincraft.ribs.navigation.NavigationInteractor
import com.perelandrax.coincraft.ribs.navigation.model.stream.NavigationMenuEventStream
import com.perelandrax.coincraft.ribs.navigation.model.stream.NavigationMenuEventStreamSource
import com.perelandrax.coincraft.ribs.navigation.model.stream.NavigationMenuEventStreamUpdater
import com.perelandrax.coincraft.ribs.navitype.NaviTypeBuilder
import com.perelandrax.coincraft.ribs.toolbar.ToolbarBuilder
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Builder for the {@link MainScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class MainBuilder(dependency: ParentComponent) :
  ViewBuilder<MainView, MainRouter, MainBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [MainRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [MainRouter].
   */
  fun build(parentViewGroup: ViewGroup): MainRouter {
    val view = createView(parentViewGroup)
    val interactor = MainInteractor()
    val component = DaggerMainBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .navigationMenuEventStream(NavigationMenuEventStream())
      .build()
    return component.mainRouter()
  }

  override fun inflateView(
    inflater: LayoutInflater,
    parentViewGroup: ViewGroup
  ): MainView? {
    return inflater.inflate(R.layout.layout_main_rib, parentViewGroup, false) as MainView
  }

  interface ParentComponent {

  }

  @dagger.Module
  abstract class Module {

    @MainScope
    @Binds
    internal abstract fun presenter(view: MainView): MainInteractor.MainPresenter

    @dagger.Module
    companion object {

      @MainScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: MainView,
        interactor: MainInteractor
      ): MainRouter {
        return MainRouter(
          view,
          interactor,
          component,
          ToolbarBuilder(component),
          NavigationBuilder(component),
          NaviTypeBuilder(component)
        )
      }

      @MainScope
      @Provides
      @JvmStatic
      internal fun provideNavigationListener(mainInteractor: MainInteractor): NavigationInteractor.Listener {
        return mainInteractor.NavigationListener()
      }

      @MainScope
      @Provides
      @JvmStatic
      internal fun provideNavigationMenuEventStreamSource(navigationMenuEventStream: NavigationMenuEventStream): NavigationMenuEventStreamSource {
        return navigationMenuEventStream
      }

      @MainScope
      @Provides
      @JvmStatic
      internal fun provideNavigationMenuEventStreamUpdater(navigationMenuEventStream: NavigationMenuEventStream): NavigationMenuEventStreamUpdater {
        return navigationMenuEventStream
      }
    }
  }

  @MainScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component :
    InteractorBaseComponent<MainInteractor>,
    BuilderComponent,
    ToolbarBuilder.ParentComponent,
    NavigationBuilder.ParentComponent,
    NaviTypeBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: MainInteractor): Builder

      @BindsInstance
      fun view(view: MainView): Builder

      @BindsInstance
      fun navigationMenuEventStream(navigationMenuEventStream: NavigationMenuEventStream): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun mainRouter(): MainRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class MainScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class MainInternal
}
