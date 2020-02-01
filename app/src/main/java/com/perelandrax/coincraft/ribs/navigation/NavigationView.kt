package com.perelandrax.coincraft.ribs.navigation

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.rxrelay2.BehaviorRelay
import com.perelandrax.coincraft.R
import io.reactivex.Observable

/**
 * Top level view for {@link MainBottomTabBuilder.MainBottomTabScope}.
 */
class NavigationView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : BottomNavigationView(context, attrs, defStyle), NavigationInteractor.MainBottomTabPresenter {

  private val menuIdEvent = BehaviorRelay.createDefault(R.id.coins).toSerialized()

  override fun onFinishInflate() {
    super.onFinishInflate()

    setOnNavigationItemSelectedListener { menuItem ->
      menuIdEvent.accept(menuItem.itemId).run { true }
    }
  }

  override fun menuIdEvent(): Observable<Int> {
    return menuIdEvent.distinctUntilChanged()
  }
}
