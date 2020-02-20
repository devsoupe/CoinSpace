package com.perelandrax.coinspace.presentation.ribs.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.data.CoinRepository
import com.perelandrax.coinspace.presentation.ribs.navigation.NavigationBuilder
import com.perelandrax.coinspace.presentation.ribs.navigation.NavigationInteractor
import com.perelandrax.coinspace.presentation.ribs.navigation.menustream.NavigationMenuEventStream
import com.perelandrax.coinspace.presentation.ribs.navigation.menustream.NavigationMenuEventStreamSource
import com.perelandrax.coinspace.presentation.ribs.navigation.menustream.NavigationMenuEventStreamUpdater
import com.perelandrax.coinspace.presentation.ribs.navitype.NaviTypeBuilder
import com.perelandrax.coinspace.presentation.ribs.splash.masterstream.CoinMasterStreamSource
import com.perelandrax.coinspace.presentation.ribs.toolbar.ToolbarBuilder
import com.perelandrax.coinspace.presentation.ribslib.ScreenStack
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

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MainView? {
    return inflater.inflate(R.layout.layout_main_rib, parentViewGroup, false) as MainView
  }

  interface ParentComponent {
    fun context(): Context
    fun screenStack(): ScreenStack
    fun coinRepository(): CoinRepository
    fun coinMasterStreamSource(): CoinMasterStreamSource
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
      internal fun router(component: Component, view: MainView, interactor: MainInteractor): MainRouter {
        return MainRouter(view, interactor, component,
          ToolbarBuilder(component),
          NavigationBuilder(component),
          NaviTypeBuilder(component)
        )
      }

      @MainScope
      @Provides
      @JvmStatic
      fun navigationListener(mainInteractor: MainInteractor): NavigationInteractor.Listener {
        return mainInteractor.NavigationListener()
      }

      @MainScope
      @Provides
      @JvmStatic
      fun navigationMenuEventStreamSource(stream: NavigationMenuEventStream): NavigationMenuEventStreamSource {
        return stream
      }

      @MainScope
      @Provides
      @JvmStatic
      fun navigationMenuEventStreamUpdater(stream: NavigationMenuEventStream): NavigationMenuEventStreamUpdater {
        return stream
      }
    }
  }

  @MainScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<MainInteractor>, BuilderComponent,
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
      fun navigationMenuEventStream(stream: NavigationMenuEventStream): Builder

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
