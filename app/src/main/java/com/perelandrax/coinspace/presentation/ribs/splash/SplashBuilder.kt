package com.perelandrax.coinspace.presentation.ribs.splash

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.data.CoinRepository
import com.perelandrax.coinspace.framework.remote.RemoteCoinDataSource
import com.perelandrax.coinspace.interactors.GetCoinMaster
import com.perelandrax.coinspace.presentation.ribs.main.MainBuilder
import com.perelandrax.coinspace.presentation.ribs.main.MainScreen
import com.perelandrax.coinspace.presentation.ribs.splash.masterstream.CoinMasterStream
import com.perelandrax.coinspace.presentation.ribs.splash.masterstream.CoinMasterStreamSource
import com.perelandrax.coinspace.presentation.ribs.splash.masterstream.CoinMasterStreamUpdater
import com.perelandrax.coinspace.presentation.screenstack.ScreenStack
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Builder for the {@link SplashScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class SplashBuilder(dependency: ParentComponent) :
  ViewBuilder<SplashView, SplashRouter, SplashBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [SplashRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [SplashRouter].
   */
  fun build(parentViewGroup: ViewGroup): SplashRouter {
    val view = createView(parentViewGroup)
    val interactor = SplashInteractor()
    val component = DaggerSplashBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .coinMasterStream(CoinMasterStream())
      .build()
    return component.splashRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): SplashView? {
    return inflater.inflate(R.layout.layout_splash_rib, parentViewGroup, false) as SplashView
  }

  interface ParentComponent {
    fun context(): Context
    fun screenStack(): ScreenStack
  }

  @dagger.Module
  abstract class Module {

    @SplashScope
    @Binds
    internal abstract fun presenter(view: SplashView): SplashInteractor.SplashPresenter

    @dagger.Module
    companion object {

      @SplashScope
      @Provides
      @JvmStatic
      internal fun router(component: Component, view: SplashView, interactor: SplashInteractor): SplashRouter {
        return SplashRouter(view, interactor, component, MainScreen(MainBuilder(component)))
      }

      @SplashScope
      @Provides
      @JvmStatic
      fun httpClient(context: Context): OkHttpClient {
        var cacheSize = 10 * 1024 * 1024L
        var cacheDirectory = File(context.cacheDir.absolutePath, "CoinSpaceCache")
        var cache = Cache(cacheDirectory, cacheSize)

        return OkHttpClient.Builder()
          .cache(cache)
          .build()
      }

      @SplashScope
      @Provides
      @JvmStatic
      fun remoteCoinDataSource(okHttpClient: OkHttpClient): RemoteCoinDataSource {
        return RemoteCoinDataSource(okHttpClient)
      }

      @SplashScope
      @Provides
      @JvmStatic
      fun coinRepository(remoteCoinDataSource: RemoteCoinDataSource): CoinRepository {
        return CoinRepository(remoteCoinDataSource)
      }

      @SplashScope
      @Provides
      @JvmStatic
      fun coinMasterStreamSource(stream: CoinMasterStream): CoinMasterStreamSource {
        return stream
      }

      @SplashScope
      @Provides
      @JvmStatic
      fun coinMasterStreamUpdater(stream: CoinMasterStream): CoinMasterStreamUpdater {
        return stream
      }

      @SplashScope
      @Provides
      @JvmStatic
      fun getCoinMaster(coinRepository: CoinRepository): GetCoinMaster {
        return GetCoinMaster(coinRepository)
      }
    }
  }

  @SplashScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<SplashInteractor>, BuilderComponent,
    MainBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: SplashInteractor): Builder

      @BindsInstance
      fun view(view: SplashView): Builder

      @BindsInstance
      fun coinMasterStream(stream: CoinMasterStream): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun splashRouter(): SplashRouter
  }

  @Scope
  @Retention(BINARY)
  internal annotation class SplashScope

  @Qualifier
  @Retention(BINARY)
  internal annotation class SplashInternal
}
