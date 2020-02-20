package com.perelandrax.coinspace.framework.appstart

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.perelandrax.coinspace.BuildConfig


class CoinSpaceApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    Logger.addLogAdapter(object : AndroidLogAdapter() {
      override fun isLoggable(priority: Int, tag: String?): Boolean {
        return BuildConfig.DEBUG
      }
    })
  }
}