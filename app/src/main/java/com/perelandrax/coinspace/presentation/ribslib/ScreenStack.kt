package com.perelandrax.coinspace.presentation.ribslib

import android.view.ViewGroup
import androidx.annotation.UiThread
import com.uber.rib.core.screenstack.ScreenStackBase
import com.uber.rib.core.screenstack.ViewProvider
import java.util.*

@UiThread
class ScreenStack(private val parentViewGroup: ViewGroup) : ScreenStackBase {

  private val backStack = ArrayDeque<ViewProvider>()
  private val currentViewProvider: ViewProvider?
    get() = if (backStack.isEmpty()) null else backStack.peek()

  override fun size(): Int = backStack.size

  override fun popBackTo(index: Int, shouldAnimate: Boolean) {
    for (size in backStack.size - 1 downTo index + 1) {
      popScreen()
    }
  }

  override fun pushScreen(viewProvider: ViewProvider?) {
    pushScreen(viewProvider, false)
  }

  override fun pushScreen(viewProvider: ViewProvider?, shouldAnimate: Boolean) {
    hideCurrentView()
    backStack.push(viewProvider)
    addCurrentView()
  }

  override fun popScreen() {
    popScreen(false)
  }

  override fun popScreen(shouldAnimate: Boolean) {
    if (backStack.isEmpty()) {
      return
    }

    removeCurrentView()
    backStack.pop()
    addCurrentView()
  }

  override fun handleBackPress(): Boolean = handleBackPress(false)

  override fun handleBackPress(shouldAnimate: Boolean): Boolean {
    if (backStack.size == 1) return false

    popScreen()
    return true
  }

  private fun addCurrentView() {
    currentViewProvider?.let {
      parentViewGroup.addView(it.buildView(parentViewGroup))
      it.onViewAppeared()
    }
  }

  private fun hideCurrentView() {
    if (parentViewGroup.childCount == 0) return

    parentViewGroup.removeViewAt(parentViewGroup.childCount - 1)
    currentViewProvider?.let {
      it.onViewHidden()
    }
  }

  private fun removeCurrentView() {
    if (parentViewGroup.childCount == 0) return

    parentViewGroup.removeViewAt(parentViewGroup.childCount - 1)
    currentViewProvider?.let {
      it.onViewRemoved()
    }
  }
}