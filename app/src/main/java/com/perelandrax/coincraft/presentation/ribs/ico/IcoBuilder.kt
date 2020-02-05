package com.perelandrax.coincraft.presentation.ribs.ico

import android.view.LayoutInflater
import android.view.ViewGroup
import com.perelandrax.coincraft.R
import com.perelandrax.coincraft.presentation.ribs.active.ActiveBuilder
import com.perelandrax.coincraft.presentation.ribs.upcoming.UpcomingBuilder
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Builder for the {@link IcoScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class IcoBuilder(dependency: ParentComponent) :
  ViewBuilder<IcoView, IcoRouter, IcoBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [IcoRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [IcoRouter].
   */
  fun build(parentViewGroup: ViewGroup): IcoRouter {
    val view = createView(parentViewGroup)
    val interactor = IcoInteractor()
    val component = DaggerIcoBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.icoRouter()
  }

  override fun inflateView(
    inflater: LayoutInflater,
    parentViewGroup: ViewGroup
  ): IcoView? {
    return inflater.inflate(R.layout.ico_rib, parentViewGroup, false) as IcoView
  }

  interface ParentComponent {
//        val icoListener: IcoInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @IcoScope @Binds
    internal abstract fun presenter(view: IcoView): IcoInteractor.IcoPresenter

    @dagger.Module
    companion object {

      @IcoScope @Provides @JvmStatic
      internal fun router(
        component: Component,
        view: IcoView,
        interactor: IcoInteractor
      ): IcoRouter {
        return IcoRouter(view, interactor, component, ActiveBuilder(component), UpcomingBuilder(component))
      }
    }
  }

  @IcoScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component :
    InteractorBaseComponent<IcoInteractor>,
    BuilderComponent,
    ActiveBuilder.ParentComponent,
    UpcomingBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: IcoInteractor): Builder

      @BindsInstance
      fun view(view: IcoView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun icoRouter(): IcoRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class IcoScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class IcoInternal
}
