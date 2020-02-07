package com.perelandrax.coincraft.ribs.upcoming

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Top level view for {@link UpcomingBuilder.UpcomingScope}.
 */
class UpcomingView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), UpcomingInteractor.UpcomingPresenter
