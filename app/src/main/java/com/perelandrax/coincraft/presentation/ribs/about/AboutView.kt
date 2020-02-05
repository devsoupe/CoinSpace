package com.perelandrax.coincraft.presentation.ribs.about

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Top level view for {@link AboutBuilder.AboutScope}.
 */
class AboutView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), AboutInteractor.AboutPresenter
