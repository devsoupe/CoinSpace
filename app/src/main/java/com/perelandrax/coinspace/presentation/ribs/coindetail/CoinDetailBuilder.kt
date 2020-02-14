package com.perelandrax.coinspace.presentation.ribs.coindetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Builder for the {@link CoinDetailScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class CoinDetailBuilder(dependency: ParentComponent) : ViewBuilder<CoinDetailView, CoinDetailRouter, CoinDetailBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [CoinDetailRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [CoinDetailRouter].
   */
  fun build(parentViewGroup: ViewGroup): CoinDetailRouter {
    val view = createView(parentViewGroup)
    val interactor = CoinDetailInteractor()
    val component = DaggerCoinDetailBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.coindetailRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): CoinDetailView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return null
  }

  interface ParentComponent {
//        val coindetailListener: CoinDetailInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @CoinDetailScope
    @Binds
    internal abstract fun presenter(view: CoinDetailView): CoinDetailInteractor.CoinDetailPresenter

    @dagger.Module
    companion object {

      @CoinDetailScope
      @Provides
      @JvmStatic
      internal fun router(component: Component, view: CoinDetailView, interactor: CoinDetailInteractor): CoinDetailRouter {
        return CoinDetailRouter(view, interactor, component)
      }

      // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }
  }

  @CoinDetailScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<CoinDetailInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: CoinDetailInteractor): Builder

      @BindsInstance
      fun view(view: CoinDetailView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun coindetailRouter(): CoinDetailRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class CoinDetailScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class CoinDetailInternal
}
