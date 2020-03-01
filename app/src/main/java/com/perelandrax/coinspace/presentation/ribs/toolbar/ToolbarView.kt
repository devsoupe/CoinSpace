package com.perelandrax.coinspace.presentation.ribs.toolbar

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur
import com.teamdecano.cryptocoin.common.screen_stack.Services
import kotlinx.android.synthetic.main.layout_toolbar_rib.view.*

/**
 * Top level view for {@link ToolbarBuilder.ToolbarScope}.
 */
class ToolbarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), ToolbarInteractor.ToolbarPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()

    setupToolbar()
    setupBlurView()
  }

  private fun setupToolbar() {
    Services.setSupportActionBar(context, toolbar)
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

  override fun updateTitle(title: String) {
    titleToolbar.text = title
  }
}
