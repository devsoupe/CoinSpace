package com.perelandrax.coinspace.presentation.ribs.coinwebsite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.presentation.ribs.coindetail.CoinDetailView
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Builder for the {@link CoinWebsiteScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class CoinWebsiteBuilder(dependency: ParentComponent) :
  ViewBuilder<CoinWebsiteView, CoinWebsiteRouter, CoinWebsiteBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [CoinWebsiteRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [CoinWebsiteRouter].
   */
  fun build(parentViewGroup: ViewGroup): CoinWebsiteRouter {
    val view = createView(parentViewGroup)
    val interactor = CoinWebsiteInteractor()
    val component = DaggerCoinWebsiteBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.coinwebsiteRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): CoinWebsiteView? {
    return inflater.inflate(R.layout.layout_coin_website_rib, parentViewGroup, false) as CoinWebsiteView
  }

  interface ParentComponent {
//    fun listener(): CoinWebsiteInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @CoinWebsiteScope
    @Binds
    internal abstract fun presenter(view: CoinWebsiteView): CoinWebsiteInteractor.CoinWebsitePresenter

    @dagger.Module
    companion object {

      @CoinWebsiteScope
      @Provides
      @JvmStatic
      internal fun router(component: Component, view: CoinWebsiteView, interactor: CoinWebsiteInteractor): CoinWebsiteRouter {
        return CoinWebsiteRouter(view, interactor, component)
      }

      // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }
  }

  @CoinWebsiteScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<CoinWebsiteInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: CoinWebsiteInteractor): Builder

      @BindsInstance
      fun view(view: CoinWebsiteView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun coinwebsiteRouter(): CoinWebsiteRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class CoinWebsiteScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class CoinWebsiteInternal
}
