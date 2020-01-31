package com.perelandrax.coincraft.ribs.mainBottomTab

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
class MainBottomTabBuilder(dependency: ParentComponent) :
  ViewBuilder<MainBottomTabView, MainBottomTabRouter, MainBottomTabBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [MainBottomTabRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [MainBottomTabRouter].
   */
  fun build(parentViewGroup: ViewGroup): MainBottomTabRouter {
    val view = createView(parentViewGroup)
    val interactor = MainBottomTabInteractor()
    val component = DaggerMainBottomTabBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.mainbottomtabRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MainBottomTabView? {
    return inflater.inflate(R.layout.main_bottom_tab_rib, parentViewGroup, false) as MainBottomTabView
  }

  interface ParentComponent {

  }

  @dagger.Module
  abstract class Module {

    @MainBottomTabScope @Binds
    internal abstract fun presenter(view: MainBottomTabView): MainBottomTabInteractor.MainBottomTabPresenter

    @dagger.Module
    companion object {

      @MainBottomTabScope @Provides @JvmStatic
      internal fun router(
        component: Component,
        view: MainBottomTabView,
        interactor: MainBottomTabInteractor
      ): MainBottomTabRouter {
        return MainBottomTabRouter(view, interactor, component)
      }

      // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }
  }

  @MainBottomTabScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component :
    InteractorBaseComponent<MainBottomTabInteractor>,
    BuilderComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: MainBottomTabInteractor): Builder

      @BindsInstance
      fun view(view: MainBottomTabView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun mainbottomtabRouter(): MainBottomTabRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class MainBottomTabScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class MainBottomTabInternal
}
