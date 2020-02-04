package com.perelandrax.coincraft.ribs.toolbar

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.teamdecano.cryptocoin.common.screen_stack.Services
import kotlinx.android.synthetic.main.toolbar_rib.view.toolbar
import kotlinx.android.synthetic.main.toolbar_rib.view.toolbar_title

/**
 * Top level view for {@link ToolbarBuilder.ToolbarScope}.
 */
class ToolbarView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), ToolbarInteractor.ToolbarPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()
    setupToolbar()
  }

  private fun setupToolbar() {
    Services.setSupportActionBar(context, toolbar)
  }

  override fun updateTitle(title: String) {
    toolbar_title.text = title
  }
}
