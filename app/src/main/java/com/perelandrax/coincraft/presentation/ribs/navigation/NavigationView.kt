package com.perelandrax.coincraft.presentation.ribs.navigation

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.jakewharton.rxrelay2.BehaviorRelay
import com.perelandrax.coincraft.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.navigation_rib.view.navigation

/**
 * Top level view for {@link MainBottomTabBuilder.MainBottomTabScope}.
 */
class NavigationView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), NavigationInteractor.MainBottomTabPresenter {

  private val menuIdEvent = BehaviorRelay.createDefault(R.id.coins).toSerialized()

  override fun onFinishInflate() {
    super.onFinishInflate()

    navigation.setOnNavigationItemSelectedListener { menuItem ->
      menuIdEvent.accept(menuItem.itemId).run { true }
    }
  }

  override fun menuIdEvent(): Observable<Int> {
    return menuIdEvent.distinctUntilChanged()
  }
}
