package com.perelandrax.coincraft.ribs.navigation

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.rxrelay2.BehaviorRelay
import com.perelandrax.coincraft.R
import com.perelandrax.coincraft.ribs.navigation.MenuEvent.*
import io.reactivex.Observable

/**
 * Top level view for {@link MainBottomTabBuilder.MainBottomTabScope}.
 */
class NavigationView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : BottomNavigationView(context, attrs, defStyle), NavigationInteractor.MainBottomTabPresenter {

  private val menuRelay = BehaviorRelay.createDefault(COINS).toSerialized()

  override fun onFinishInflate() {
    super.onFinishInflate()

    setOnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        R.id.coins -> menuRelay.accept(COINS)
        R.id.ico -> menuRelay.accept(ICO)
        R.id.about -> menuRelay.accept(ABOUT)
      }

      true
    }
  }

  override fun menuEvents(): Observable<MenuEvent> {
    return menuRelay.distinctUntilChanged()
  }
}
