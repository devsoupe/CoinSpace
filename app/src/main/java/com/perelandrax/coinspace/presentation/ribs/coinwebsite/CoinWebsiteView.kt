package com.perelandrax.coinspace.presentation.ribs.coinwebsite

import android.content.Context
import android.util.AttributeSet
import com.perelandrax.coinspace.presentation.ribslib.AnimationFrameLayout

/**
 * Top level view for {@link CoinWebsiteBuilder.CoinWebsiteScope}.
 */
class CoinWebsiteView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  AnimationFrameLayout(context, attrs, defStyle), CoinWebsiteInteractor.CoinWebsitePresenter
