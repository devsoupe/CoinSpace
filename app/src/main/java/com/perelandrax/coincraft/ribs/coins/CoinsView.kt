package com.perelandrax.coincraft.ribs.coins

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Top level view for {@link CoinsBuilder.CoinsScope}.
 */
class CoinsView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), CoinsInteractor.CoinsPresenter
