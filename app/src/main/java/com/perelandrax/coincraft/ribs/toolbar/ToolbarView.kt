package com.perelandrax.coincraft.ribs.toolbar

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Top level view for {@link ToolbarBuilder.ToolbarScope}.
 */
class ToolbarView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), ToolbarInteractor.ToolbarPresenter
