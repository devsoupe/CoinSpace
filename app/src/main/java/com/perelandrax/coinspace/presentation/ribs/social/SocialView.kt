package com.perelandrax.coinspace.presentation.ribs.social

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Top level view for {@link SocialBuilder.SocialScope}.
 */
class SocialView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), SocialInteractor.SocialPresenter
