package com.perelandrax.coinspace.presentation.coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface CoroutineScopeProvider : CoroutineScope {

  companion object {
    val jobContainer = HashMap<Any, CompletableJob>()
  }

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.IO + parentJob

  val parentJob
    get() = jobContainer[this] ?: SupervisorJob().also { jobContainer[this] = it }

  suspend fun dispatchUi(job: () -> Unit) {
    withContext(Dispatchers.Main) {
      job()
    }
  }
}