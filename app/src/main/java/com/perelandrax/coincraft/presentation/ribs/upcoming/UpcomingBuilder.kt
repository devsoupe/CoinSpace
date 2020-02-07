package com.perelandrax.coincraft.presentation.ribs.upcoming

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
 * Builder for the {@link UpcomingScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class UpcomingBuilder(dependency: ParentComponent) :
  ViewBuilder<UpcomingView, UpcomingRouter, UpcomingBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [UpcomingRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [UpcomingRouter].
   */
  fun build(parentViewGroup: ViewGroup): UpcomingRouter {
    val view = createView(parentViewGroup)
    val interactor = UpcomingInteractor()
    val component = DaggerUpcomingBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.upcomingRouter()
  }

  override fun inflateView(
    inflater: LayoutInflater,
    parentViewGroup: ViewGroup
  ): UpcomingView? {
    return inflater.inflate(R.layout.layout_upcoming_rib, parentViewGroup, false) as UpcomingView
  }

  interface ParentComponent {

  }

  @dagger.Module
  abstract class Module {

    @UpcomingScope
    @Binds
    internal abstract fun presenter(view: UpcomingView): UpcomingInteractor.UpcomingPresenter

    @dagger.Module
    companion object {

      @UpcomingScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: UpcomingView,
        interactor: UpcomingInteractor
      ): UpcomingRouter {
        return UpcomingRouter(view, interactor, component)
      }

      // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }
  }

  @UpcomingScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<UpcomingInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: UpcomingInteractor): Builder

      @BindsInstance
      fun view(view: UpcomingView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun upcomingRouter(): UpcomingRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class UpcomingScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class UpcomingInternal
}
