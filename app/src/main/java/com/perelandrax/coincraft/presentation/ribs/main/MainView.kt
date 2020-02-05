package com.perelandrax.coincraft.presentation.ribs.main

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Top level view for {@link MainBuilder.MainScope}.
 */
class MainView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), MainInteractor.MainPresenter
