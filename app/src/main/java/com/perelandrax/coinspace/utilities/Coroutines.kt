package com.perelandrax.coinspace.utilities

import com.orhanobut.logger.Logger
import kotlin.coroutines.CoroutineContext

class Coroutines {

  companion object {

    fun log(methodName: String, coroutineContext: CoroutineContext) {
      Logger.d(
        "TestCoroutine", "Thread for $methodName is: ${Thread.currentThread().name}" +
          "and the context is $coroutineContext"
      )
    }
  }
}