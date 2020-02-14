package com.perelandrax.coinspace.presentation.ribs.coindetail

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Top level view for {@link CoinDetailBuilder.CoinDetailScope}.
 */
class CoinDetailView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), CoinDetailInteractor.CoinDetailPresenter
