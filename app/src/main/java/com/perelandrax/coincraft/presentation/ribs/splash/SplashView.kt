package com.perelandrax.coincraft.presentation.ribs.splash

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Top level view for {@link SplashBuilder.SplashScope}.
 */
class SplashView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), SplashInteractor.SplashPresenter
