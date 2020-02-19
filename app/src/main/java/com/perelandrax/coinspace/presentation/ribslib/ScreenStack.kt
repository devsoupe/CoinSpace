package com.perelandrax.coinspace.presentation.ribslib

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.view.ViewGroup
import androidx.annotation.UiThread
import com.perelandrax.coinspace.R
import com.uber.rib.core.screenstack.ScreenStackBase
import com.uber.rib.core.screenstack.ViewProvider
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
    backStack.push(viewProvider)
    pushCurrentView(R.anim.slide_to_left, R.anim.slide_from_right)
  }

  override fun popScreen() {
    popScreen(false)
  }

  override fun popScreen(shouldAnimate: Boolean) {
    if (backStack.isEmpty()) {
      return
    }

    popCurrentView(R.anim.slide_to_right, R.anim.slide_from_left)
    backStack.pop()
  }

  override fun handleBackPress(): Boolean = handleBackPress(false)

  override fun handleBackPress(shouldAnimate: Boolean): Boolean {
    if (backStack.size == 1) return false

    popScreen()
    return true
  }

  fun replaceScreen(viewProvider: ViewProvider?, outAnimId: Int = -1, inAnimId: Int = -1) {
    hideCurrentView(outAnimId)
    if (!backStack.isEmpty()) {
      backStack.pop()
    }

    backStack.push(viewProvider)
    addCurrentView(inAnimId)
  }

  private fun addCurrentView(inAnimId: Int) {
    currentViewProvider?.let {

//      val fade = Fade(Fade.IN)
//      val slide = Slide(Gravity.RIGHT)
//      TransitionManager.beginDelayedTransition(parentViewGroup, slide)

      val view = it.buildView(parentViewGroup)
//      SlideAnimationUtil.slideInFromRight(parentViewGroup.context, view)

      if (inAnimId != -1) {
        SlideAnimationUtil.runSimpleAnimation(parentViewGroup.context, view, inAnimId)
      }

      parentViewGroup.addView(view)
      it.onViewAppeared()
    }
  }

  private fun pushCurrentView(outAnimId: Int, inAnimId: Int) {
    currentViewProvider?.let {

      val outView = parentViewGroup.getChildAt(parentViewGroup.childCount - 1)
      val inView = it.buildView(parentViewGroup)

//      if (outAnimId != -1) {
//        SlideAnimationUtil.runSimpleAnimation(parentViewGroup.context, outView, outAnimId)
//      }
//
//      if (inAnimId != -1) {
//        SlideAnimationUtil.runSimpleAnimation(parentViewGroup.context, inView, inAnimId)
//      }

//      val animY = ObjectAnimator.ofFloat(outView, "translationX", -200f);
//      val animX = ObjectAnimator.ofFloat(inView, "translationX", 0f, -inView.width.toFloat());
//
//      val animSetXY = AnimatorSet();
//      animSetXY.playTogether(animY, animX);
//      animSetXY.duration = 300
//      animSetXY.start()

      val outAnimation = AnimatorInflater.loadAnimator(parentViewGroup.context, R.animator.obj_slide_to_left)
      val inAnimation = AnimatorInflater.loadAnimator(parentViewGroup.context, R.animator.obj_slide_from_right)

      val animatorSet = AnimatorSet()

      outAnimation.setTarget(outView)
      inAnimation.setTarget(inView)

      animatorSet.playTogether(outAnimation, inAnimation)
      animatorSet.start()

//      val outViewAnimation = SlideAnimationUtil.loadAnimation(parentViewGroup.context, outView, outAnimId)
//      val inViewAnimation = SlideAnimationUtil.loadAnimation(parentViewGroup.context, inView, inAnimId)
//
//      val animationSet = AnimationSet(true)
//      animationSet.addAnimation(outViewAnimation)
//      animationSet.addAnimation(inViewAnimation)
//      animationSet.start()

      parentViewGroup.addView(inView)
      it.onViewAppeared()
    }
  }

  private fun popCurrentView(outAnimId: Int, inAnimId: Int) {
    if (parentViewGroup.childCount == 0) return

    val outView = parentViewGroup.getChildAt(parentViewGroup.childCount - 1)
    val inView = parentViewGroup.getChildAt(parentViewGroup.childCount - 2)

//    if (outAnimId != -1) {
//      SlideAnimationUtil.runSimpleAnimation(parentViewGroup.context, outView, outAnimId)
//    }
//
//    if (inAnimId != -1) {
//      SlideAnimationUtil.runSimpleAnimation(parentViewGroup.context, inView, inAnimId)
//    }

    val outAnimation = AnimatorInflater.loadAnimator(parentViewGroup.context, R.animator.obj_slide_to_right)
    val inAnimation = AnimatorInflater.loadAnimator(parentViewGroup.context, R.animator.obj_slide_from_left)

    val animatorSet = AnimatorSet()

    outAnimation.setTarget(outView)
    inAnimation.setTarget(inView)

    animatorSet.playTogether(outAnimation, inAnimation)
    animatorSet.start()

    animatorSet.addListener(object : Animator.AnimatorListener {
      override fun onAnimationRepeat(animation: Animator?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }

      override fun onAnimationEnd(animation: Animator?) {
        parentViewGroup.removeView(outView)
        currentViewProvider?.let {
          it.onViewRemoved()
        }
      }

      override fun onAnimationCancel(animation: Animator?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }

      override fun onAnimationStart(animation: Animator?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }
    })
  }

  private fun hideCurrentView(outAnimId: Int) {
    if (parentViewGroup.childCount == 0) return

//    val slide = Slide(Gravity.LEFT)
//    TransitionManager.beginDelayedTransition(parentViewGroup, slide)

    val view = parentViewGroup.getChildAt(parentViewGroup.childCount - 1)
    //SlideAnimationUtil.slideOutToLeft(parentViewGroup.context, view)

    if (outAnimId != -1) {
      SlideAnimationUtil.runSimpleAnimation(parentViewGroup.context, view, outAnimId)
    }

    parentViewGroup.removeView(view)
    currentViewProvider?.let {
      it.onViewHidden()
    }
  }

  private fun removeCurrentView(outAnimId: Int) {
    if (parentViewGroup.childCount == 0) return

//    val slide = Slide(Gravity.BOTTOM)
//    TransitionManager.beginDelayedTransition(parentViewGroup, slide)

    val view = parentViewGroup.getChildAt(parentViewGroup.childCount - 1)
    //SlideAnimationUtil.slideOutToLeft(parentViewGroup.context, view)


    if (outAnimId != -1) {
      SlideAnimationUtil.runSimpleAnimation(parentViewGroup.context, view, outAnimId)
    }

    parentViewGroup.removeView(view)
    currentViewProvider?.let {
      it.onViewRemoved()
    }
  }
}