package com.perelandrax.coinspace.presentation.ribs.navitype

import android.content.Context
import com.perelandrax.coinspace.data.CoinRepository
import com.perelandrax.coinspace.presentation.ribs.about.AboutBuilder
import com.perelandrax.coinspace.presentation.ribs.coins.CoinsBuilder
import com.perelandrax.coinspace.presentation.ribs.main.MainView
import com.perelandrax.coinspace.presentation.ribs.navigation.menustream.NavigationMenuEventStreamSource
import com.perelandrax.coinspace.presentation.ribs.news.NewsBuilder
import com.perelandrax.coinspace.presentation.ribs.splash.masterstream.CoinMasterStreamSource
import com.perelandrax.coinspace.presentation.ribslib.ScreenStack
import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link NaviTypeScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class NaviTypeBuilder(dependency: ParentComponent) :
  Builder<NaviTypeRouter, NaviTypeBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [NaviTypeRouter].
   *
   * @return a new [NaviTypeRouter].
   */
  fun build(): NaviTypeRouter {
    val interactor = NaviTypeInteractor()
    val component = DaggerNaviTypeBuilder_Component.builder()
      .parentComponent(dependency)
      .interactor(interactor)
      .build()

    return component.navitypeRouter()
  }

  interface ParentComponent {
    fun context(): Context
    fun screenStack(): ScreenStack
    fun mainView(): MainView
    fun coinRepository(): CoinRepository
    fun navigationMenuEventStreamSource(): NavigationMenuEventStreamSource
    fun coinMasterStreamSource(): CoinMasterStreamSource
  }

  @dagger.Module
  abstract class Module {

    @dagger.Module
    companion object {

      @NaviTypeScope
      @Provides
      @JvmStatic
      internal fun presenter(): EmptyPresenter {
        return EmptyPresenter()
      }

      @NaviTypeScope
      @Provides
      @JvmStatic
      internal fun router(component: Component, interactor: NaviTypeInteractor, mainView: MainView): NaviTypeRouter {
        return NaviTypeRouter(
          interactor, component, mainView,
          CoinsBuilder(component), NewsBuilder(component), AboutBuilder(component)
        )
      }

      // TODO: Create provider methods for dependencies created by this Rib. These methods should be static.
    }
  }

  @NaviTypeScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<NaviTypeInteractor>, BuilderComponent,
    CoinsBuilder.ParentComponent,
    NewsBuilder.ParentComponent,
    AboutBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: NaviTypeInteractor): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }

  }

  interface BuilderComponent {
    fun navitypeRouter(): NaviTypeRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class NaviTypeScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class NaviTypeInternal
}
