package com.perelandrax.coincraft.ribs.ico

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Top level view for {@link IcoBuilder.IcoScope}.
 */
class IcoView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), IcoInteractor.IcoPresenter
