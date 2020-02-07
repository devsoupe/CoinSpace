package com.perelandrax.coincraft.ribs.active

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Top level view for {@link ActiveBuilder.ActiveScope}.
 */
class ActiveView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), ActiveInteractor.ActivePresenter
