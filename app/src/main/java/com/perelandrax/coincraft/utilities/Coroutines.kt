package com.perelandrax.coincraft.utilities

import android.util.Log
import kotlin.coroutines.CoroutineContext

class Coroutines {

  companion object {

    fun log(methodName: String, coroutineContext: CoroutineContext) {
      Log.d(
        "TestCoroutine", "Thread for $methodName is: ${Thread.currentThread().name}" +
          "and the context is $coroutineContext"
      )
    }
  }
}