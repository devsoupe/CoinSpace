package com.perelandrax.coinspace.presentation.ribs.navigation

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur
import com.jakewharton.rxrelay2.BehaviorRelay
import com.perelandrax.coinspace.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.layout_navigation_rib.view.*

/**
 * Top level view for {@link MainBottomTabBuilder.MainBottomTabScope}.
 */
class NavigationView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), NavigationInteractor.MainBottomTabPresenter {

  private val menuIdEvent = BehaviorRelay.createDefault(R.id.coins)
    .toSerialized()

  override fun onFinishInflate() {
    super.onFinishInflate()

    setupBlurView()
    navigation.setOnNavigationItemSelectedListener { menuItem ->
      menuIdEvent.accept(menuItem.itemId)
        .run { true }
    }
  }

  private fun setupBlurView() {
    val radius = 15f
    val windowBackground = (context as Activity).window.decorView.background
    val rootView = (context as Activity).window.decorView.rootView as ViewGroup

    blurView.setupWith(rootView)
      .setFrameClearDrawable(windowBackground)
      .setBlurAlgorithm(SupportRenderScriptBlur(context))
      .setBlurRadius(radius)
      .setHasFixedTransformationMatrix(true)
  }

  override fun menuIdEvent(): Observable<Int> {
    return menuIdEvent.distinctUntilChanged()
  }
}
