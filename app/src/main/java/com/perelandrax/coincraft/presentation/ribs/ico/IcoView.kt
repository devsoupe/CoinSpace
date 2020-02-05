package com.perelandrax.coincraft.presentation.ribs.ico

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewpager.widget.PagerAdapter
import com.perelandrax.coincraft.presentation.ribs.ico.view.tablayout.TabLayoutView
import kotlinx.android.synthetic.main.coins_rib.view.loadingView
import kotlinx.android.synthetic.main.ico_rib.view.tabLayout
import kotlinx.android.synthetic.main.ico_rib.view.viewPager

/**
 * Top level view for {@link IcoBuilder.IcoScope}.
 */
class IcoView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), IcoInteractor.IcoPresenter {

  private lateinit var adapter: IcoPagerAdapter

  override fun onFinishInflate() {
    super.onFinishInflate()
    setupLoadingView()
  }

  private fun setupLoadingView() {
    loadingView.speed = 1.25f
  }

  override fun setupTabLayoutViews(tabLayoutViews: List<TabLayoutView>) {
    tabLayout.setupWithViewPager(viewPager)

    for (tabLayoutView in tabLayoutViews) {
      tabLayout.addTab(tabLayout.newTab())
    }

    adapter = IcoPagerAdapter(tabLayoutViews)
    viewPager.adapter = adapter
  }

  inner class IcoPagerAdapter(val tabLayoutViews: List<TabLayoutView>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
      return tabLayoutViews[position].view.apply { container.addView(this) }
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
      container.removeView(view as View)
    }

    override fun getCount(): Int {
      return tabLayoutViews.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
      return tabLayoutViews[position].title
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
      return (view as Any) == obj
    }
  }
}
