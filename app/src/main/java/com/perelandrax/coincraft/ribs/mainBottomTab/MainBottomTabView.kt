package com.perelandrax.coincraft.ribs.mainBottomTab

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Top level view for {@link MainBottomTabBuilder.MainBottomTabScope}.
 */
class MainBottomTabView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : BottomNavigationView(context, attrs, defStyle), MainBottomTabInteractor.MainBottomTabPresenter
