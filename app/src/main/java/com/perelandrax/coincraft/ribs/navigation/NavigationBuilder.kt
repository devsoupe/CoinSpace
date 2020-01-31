package com.perelandrax.coincraft.ribs.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.perelandrax.coincraft.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Builder for the {@link MainBottomTabScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class NavigationBuilder(dependency: ParentComponent) :
  ViewBuilder<NavigationView, NavigationRouter, NavigationBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [NavigationRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [NavigationRouter].
   */
  fun build(parentViewGroup: ViewGroup): NavigationRouter {
    val view = createView(parentViewGroup)
    val interactor = NavigationInteractor()
    val component = DaggerNavigationBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.mainbottomtabRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): NavigationView? {
    return inflater.inflate(R.layout.main_bottom_tab_rib, parentViewGroup, false) as NavigationView
  }

  interface ParentComponent {
    fun navigationListener(): NavigationInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @MainBottomTabScope @Binds
    internal abstract fun presenter(view: NavigationView): NavigationInteractor.MainBottomTabPresenter

    @dagger.Module
    companion object {

      @MainBottomTabScope @Provides @JvmStatic
      internal fun router(
        component: Component,
        view: NavigationView,
        interactor: NavigationInteractor
      ): NavigationRouter {
        return NavigationRouter(view, interactor, component)
      }

      // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }
  }

  @MainBottomTabScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component :
    InteractorBaseComponent<NavigationInteractor>,
    BuilderComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: NavigationInteractor): Builder

      @BindsInstance
      fun view(view: NavigationView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun mainbottomtabRouter(): NavigationRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class MainBottomTabScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class MainBottomTabInternal
}
