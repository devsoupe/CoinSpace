package com.perelandrax.coinspace.presentation.ribslib

import android.view.Gravity
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.uber.rib.core.screenstack.ScreenStackBase
import com.uber.rib.core.screenstack.ViewProvider
import kotlinx.android.synthetic.main.layout_coins_rib.view.*
import kotlinx.android.synthetic.main.layout_root_rib.view.*
import java.util.*

@UiThread
class ScreenStack(private val parentViewGroup: ViewGroup) : ScreenStackBase {

  private val backStack = Stack<ViewProvider>()
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
//    hideCurrentView()
    backStack.push(viewProvider)
    addCurrentView()
  }

  fun replaceScreen(viewProvider: ViewProvider?) {
    removeCurrentView()
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
//    addCurrentView()
  }

  override fun handleBackPress(): Boolean = handleBackPress(false)

  override fun handleBackPress(shouldAnimate: Boolean): Boolean {
    if (backStack.size == 1) return false

    popScreen()
    return true
  }

  private fun addCurrentView() {
    currentViewProvider?.let {

//      val transition = Slide(Gravity.BOTTOM)
//      parentViewGroup.container.addView(it.build                                                         View(parentViewGroup))
//      TransitionManager.beginDelayedTransition(parentViewGroup.container, transition);
//      it.onViewAppeared()

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