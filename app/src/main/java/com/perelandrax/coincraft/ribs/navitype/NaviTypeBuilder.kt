package com.perelandrax.coincraft.ribs.navitype

import com.perelandrax.coincraft.ribs.about.AboutBuilder
import com.perelandrax.coincraft.ribs.coins.CoinsBuilder
import com.perelandrax.coincraft.ribs.ico.IcoBuilder
import com.perelandrax.coincraft.ribs.main.MainView
import com.perelandrax.coincraft.ribs.navigation.model.stream.NavigationMenuEventStreamSource
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
    fun mainView(): MainView
    fun navigationMenuEventStreamSource(): NavigationMenuEventStreamSource
  }

  @dagger.Module
  abstract class Module {

    @dagger.Module
    companion object {

      @NaviTypeScope @Provides @JvmStatic
      internal fun presenter(): EmptyPresenter {
        return EmptyPresenter()
      }

      @NaviTypeScope @Provides @JvmStatic
      internal fun router(component: Component, interactor: NaviTypeInteractor, mainView: MainView): NaviTypeRouter {
        return NaviTypeRouter(interactor, component, mainView,
          CoinsBuilder(component), IcoBuilder(component), AboutBuilder(component))
      }

      // TODO: Create provider methods for dependencies created by this Rib. These methods should be static.
    }
  }

  @NaviTypeScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component :
    InteractorBaseComponent<NaviTypeInteractor>,
    BuilderComponent,
    CoinsBuilder.ParentComponent,
    IcoBuilder.ParentComponent,
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
