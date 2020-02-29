package com.perelandrax.coinspace.framework.appstart

import android.app.Application
import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.perelandrax.coinspace.BuildConfig
import eightbitlab.com.blurview.BlurAlgorithm

class CoinSpaceApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    setupLogger()
//    setupBlurView()
  }

  private fun setupLogger() {
    val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
      .methodCount(0)
      .methodOffset(0)
      .build()

    Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
      override fun isLoggable(priority: Int, tag: String?): Boolean {
        return BuildConfig.DEBUG
      }
    })
  }

//  private fun setupBlurView() {
//    blurAlgorithm(SupportRenderScriptBlur(this))
//  }
}