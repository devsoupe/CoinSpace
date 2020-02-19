package com.perelandrax.coinspace.presentation.ribslib

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.perelandrax.coinspace.R


object SlideAnimationUtil {

  fun slideInFromLeft(context: Context, view: View) {
    runSimpleAnimation(context, view, R.anim.slide_from_left)
  }

  fun slideOutToLeft(context: Context, view: View) {
    runSimpleAnimation(context, view, R.anim.slide_to_left)
  }

  fun slideInFromRight(context: Context, view: View) {
    runSimpleAnimation(context, view, R.anim.slide_from_right)
  }

  fun slideOutToRight(context: Context, view: View) {
    runSimpleAnimation(context, view, R.anim.slide_to_right)
  }

  fun runSimpleAnimation(context: Context, view: View, animationId: Int) {
    view.startAnimation(AnimationUtils.loadAnimation(context, animationId))
  }

  fun loadAnimation(context: Context, view: View, animationId: Int): Animation {
    return AnimationUtils.loadAnimation(context, animationId)
  }
}