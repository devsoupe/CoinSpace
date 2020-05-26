package com.perelandrax.coinspace.presentation.screenstack

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.view.ViewGroup
import androidx.annotation.UiThread
import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.presentation.screenstack.ScreenProvider.ViewType
import com.uber.rib.core.screenstack.ScreenStackBase
import com.uber.rib.core.screenstack.ViewProvider
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.atomic.AtomicBoolean

@UiThread
class ScreenStackHelper(private val parentViewGroup: ViewGroup) : ScreenStackBase, ScreenStack {

  private val backStack = LinkedBlockingDeque<ScreenProvider>()
  private val screenProvider: ScreenProvider?
    get() = if (backStack.isEmpty()) null else backStack.peek()

  private val showingAnimation = AtomicBoolean(false)

  override fun size(): Int = backStack.size

  override fun popBackTo(index: Int, shouldAnimate: Boolean) {
    for (size in backStack.size - 1 downTo index + 1) {
      popScreen()
    }
  }

  override fun pushScreen(viewProvider: ViewProvider?) {
//    pushScreen(viewProvider, false)
  }

  override fun pushScreen(viewProvider: ViewProvider?, shouldAnimate: Boolean) {
//    backStack.push(viewProvider)
//    pushCurrentView()
  }

  override fun popScreen() {
    popScreen(false)
  }

  override fun popScreen(shouldAnimate: Boolean) {
    if (backStack.isEmpty()) {
      return
    }

    popCurrentView()
    backStack.pop()
  }

  override fun handleBackPress(): Boolean = handleBackPress(false)

  override fun handleBackPress(shouldAnimate: Boolean): Boolean {
    if (showingAnimation.get()) return true
    if (backStack.size == 1) return false

    popScreen()
    return true
  }

  private fun popCurrentView() {
    var outAnimId = R.animator.fade_out
    var inAnimId = R.animator.fade_in

    screenProvider?.let {
      when (it.viewType) {
        ViewType.PUSH -> {
          outAnimId = R.animator.slide_to_right; inAnimId = R.animator.slide_from_left
        }
        ViewType.PRESENT -> {
          outAnimId = R.animator.slide_to_down;inAnimId = R.animator.slide_from_up
        }
        ViewType.STACK -> {
          outAnimId = R.animator.slide_to_down_fade_out; inAnimId = R.animator.scale_expand
        }
      }
    }

    if (parentViewGroup.childCount == 0) return

    val outView = parentViewGroup.getChildAt(parentViewGroup.childCount - 1)
    val inView = parentViewGroup.getChildAt(parentViewGroup.childCount - 2)

    val outAnimation = AnimatorInflater.loadAnimator(parentViewGroup.context, outAnimId)
    val inAnimation = AnimatorInflater.loadAnimator(parentViewGroup.context, inAnimId)

    val animatorSet = AnimatorSet()

    outAnimation.setTarget(outView)
    inAnimation.setTarget(inView)

    lateinit var previousViewProvider: ViewProvider

    animatorSet.playTogether(outAnimation, inAnimation)
    animatorSet.addListener(object : AnimatorListenerAdapter() {

      override fun onAnimationStart(animation: Animator?) {
        super.onAnimationStart(animation)
        showingAnimation.set(true)
      }

      override fun onAnimationEnd(animation: Animator?) {
        previousViewProvider.onViewRemoved()

        parentViewGroup.removeView(outView)
        screenProvider?.let {
          it.viewProvider.onViewAppeared()
        }
        showingAnimation.set(false)
      }
    })

    animatorSet.start()

    screenProvider?.let { previousViewProvider = it.viewProvider }
  }

  private fun removeCurrentView(outAnimId: Int) {
    if (parentViewGroup.childCount == 0) return

//    val slide = Slide(Gravity.BOTTOM)
//    TransitionManager.beginDelayedTransition(parentViewGroup, slide)

    val view = parentViewGroup.getChildAt(parentViewGroup.childCount - 1)
    //SlideAnimationUtil.slideOutToLeft(parentViewGroup.context, view)


    if (outAnimId != -1) {
//      SlideAnimationUtil.runSimpleAnimation(parentViewGroup.context, view, outAnimId)
    }

    parentViewGroup.removeView(view)
    screenProvider?.let {
      it.viewProvider.onViewRemoved()
    }
  }

  override fun addEmptyScreen() {
    backStack.push(ScreenProvider(EmptyScreenProvider(), ViewType.REPLACE))
  }

  override fun replace(viewProvider: ViewProvider) {
    hideCurrentView()
    if (!backStack.isEmpty()) {
      backStack.pop()
    }

    backStack.push(ScreenProvider(viewProvider, ViewType.REPLACE))
    addCurrentView()
  }

  override fun push(viewProvider: ViewProvider) {
//    screenProvider?.let {
//      it.viewProvider.onViewHidden()
//    }

    backStack.push(ScreenProvider(viewProvider, ViewType.PUSH))
    showCurrentView(R.animator.slide_from_right, R.animator.slide_to_left)
  }

  override fun present(viewProvider: ViewProvider) {
//    screenProvider?.let {
//      it.viewProvider.onViewHidden()
//    }

    backStack.push(ScreenProvider(viewProvider, ViewType.PRESENT))
    showCurrentView(R.animator.slide_from_down, R.animator.slide_to_up)
  }

  override fun stack(viewProvider: ViewProvider) {
//    screenProvider?.let {
//      it.viewProvider.onViewHidden()
//    }

    backStack.push(ScreenProvider(viewProvider, ViewType.STACK))
    showCurrentView(R.animator.slide_from_down_fade_in, R.animator.scale_contract)
  }

  override fun back(): Boolean {
    return handleBackPress()
  }

  override fun isAnimating(): Boolean {
    return showingAnimation.get()
  }

  override fun getScreenStackCount(): Int {
    return backStack.size
  }

  private fun hideCurrentView() {
    if (parentViewGroup.childCount == 0) return

    val view = parentViewGroup.getChildAt(parentViewGroup.childCount - 1)
    val animation = AnimatorInflater.loadAnimator(parentViewGroup.context, R.animator.fade_out)

    animation.setTarget(view)
    animation.addListener(object : AnimatorListenerAdapter() {

      override fun onAnimationStart(animation: Animator?) {
        super.onAnimationStart(animation)
        showingAnimation.set(true)
      }

      override fun onAnimationEnd(animation: Animator?) {
        parentViewGroup.removeView(view)
        showingAnimation.set(false)
      }
    })

    animation.start()

    screenProvider?.let {
      it.viewProvider.onViewHidden()
    }
  }

  private fun addCurrentView() {
    screenProvider?.let {
      val view = it.viewProvider.buildView(parentViewGroup)
      val animation = AnimatorInflater.loadAnimator(parentViewGroup.context, R.animator.fade_in)

      animation.setTarget(view)
      animation.addListener(object : AnimatorListenerAdapter() {

        override fun onAnimationStart(animation: Animator?) {
          super.onAnimationStart(animation)
          showingAnimation.set(true)
        }

        override fun onAnimationEnd(animation: Animator?) {
          it.viewProvider.onViewAppeared()
        }
      })

      animation.start()

      parentViewGroup.addView(view)
    }
  }

  private fun showCurrentView(inAnimId: Int, outAnimId: Int) {
    screenProvider?.let {
      val inView = it.viewProvider.buildView(parentViewGroup)
      val outView = parentViewGroup.getChildAt(parentViewGroup.childCount - 1)

      val inAnimation = AnimatorInflater.loadAnimator(parentViewGroup.context, inAnimId)
      val outAnimation = AnimatorInflater.loadAnimator(parentViewGroup.context, outAnimId)

      val animatorSet = AnimatorSet()

      inAnimation.setTarget(inView)
      outAnimation.setTarget(outView)

      animatorSet.playTogether(outAnimation, inAnimation)
      animatorSet.addListener(object : AnimatorListenerAdapter() {

        override fun onAnimationStart(animation: Animator?) {
          super.onAnimationStart(animation)
          showingAnimation.set(true)
        }

        override fun onAnimationEnd(animation: Animator?) {
          it.viewProvider.onViewAppeared()
          showingAnimation.set(false)
        }
      })

      animatorSet.start()

      parentViewGroup.addView(inView)
    }
  }
}