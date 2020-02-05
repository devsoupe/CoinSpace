package com.perelandrax.coincraft.presentation.ribs.active

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
 * Builder for the {@link ActiveScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class ActiveBuilder(dependency: ParentComponent) :
  ViewBuilder<ActiveView, ActiveRouter, ActiveBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [ActiveRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [ActiveRouter].
   */
  fun build(parentViewGroup: ViewGroup): ActiveRouter {
    val view = createView(parentViewGroup)
    val interactor = ActiveInteractor()
    val component = DaggerActiveBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.activeRouter()
  }

  override fun inflateView(
    inflater: LayoutInflater,
    parentViewGroup: ViewGroup
  ): ActiveView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return inflater.inflate(R.layout.active_rib, parentViewGroup, false) as ActiveView
  }

  interface ParentComponent {

  }

  @dagger.Module
  abstract class Module {

    @ActiveScope
    @Binds
    internal abstract fun presenter(view: ActiveView): ActiveInteractor.ActivePresenter

    @dagger.Module
    companion object {

      @ActiveScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: ActiveView,
        interactor: ActiveInteractor
      ): ActiveRouter {
        return ActiveRouter(view, interactor, component)
      }

      // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }
  }

  @ActiveScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<ActiveInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: ActiveInteractor): Builder

      @BindsInstance
      fun view(view: ActiveView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun activeRouter(): ActiveRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class ActiveScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class ActiveInternal
}
