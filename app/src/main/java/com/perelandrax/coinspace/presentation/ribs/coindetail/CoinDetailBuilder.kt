package com.perelandrax.coinspace.presentation.ribs.coindetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.data.CoinRepository
import com.perelandrax.coinspace.domain.coindetail.CoinDetail
import com.perelandrax.coinspace.interactors.GetCoinDetail
import com.perelandrax.coinspace.presentation.coroutine.CoroutineScopeProvider
import com.perelandrax.coinspace.presentation.ribs.coinwebsite.CoinWebsiteBuilder
import com.perelandrax.coinspace.presentation.ribs.coinwebsite.CoinWebsiteScreen
import com.perelandrax.coinspace.presentation.screenstack.ScreenStack
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
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
  fun build(parentViewGroup: ViewGroup, coinId: String): CoinDetailRouter {
    val view = createView(parentViewGroup)
    val interactor = CoinDetailInteractor()
    val component = DaggerCoinDetailBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .coinId(coinId)
      .build()
    return component.coindetailRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): CoinDetailView? {
    return inflater.inflate(R.layout.layout_coin_detail_rib, parentViewGroup, false) as CoinDetailView
  }

  interface ParentComponent {
    fun screenStack(): ScreenStack
    fun coinRepository(): CoinRepository
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
      internal fun router(component: Component, view: CoinDetailView, interactor: CoinDetailInteractor, screenStack: ScreenStack): CoinDetailRouter {
        return CoinDetailRouter(view, interactor, component, screenStack, CoinWebsiteScreen(CoinWebsiteBuilder(component)))
      }

      @CoinDetailScope
      @Provides
      @JvmStatic
      fun coroutineScopeProvider(interactor: CoinDetailInteractor): CoroutineScopeProvider {
        return interactor
      }

      @CoinDetailScope
      @Provides
      @JvmStatic
      fun getCoinDetail(coroutineScopeProvider: CoroutineScopeProvider, coinRepository: CoinRepository, coinId: String): GetCoinDetail {
        return GetCoinDetail(coroutineScopeProvider, coinId, coinRepository)
      }
    }
  }

  @CoinDetailScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<CoinDetailInteractor>, BuilderComponent,
    CoinWebsiteBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: CoinDetailInteractor): Builder

      @BindsInstance
      fun view(view: CoinDetailView): Builder

      @BindsInstance
      fun coinId(coinId: String): Builder

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
