package com.perelandrax.coinspace.presentation.ribs.news

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewpager.widget.PagerAdapter
import com.perelandrax.coinspace.presentation.ribs.news.tablayout.TabLayoutView
import kotlinx.android.synthetic.main.layout_news_rib.view.*

/**
 * Top level view for {@link NewsBuilder.NewsScope}.
 */
class NewsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), NewsInteractor.NewsPresenter {

  private lateinit var adapter: NewsPagerAdapter

  override fun onFinishInflate() {
    super.onFinishInflate()
    setupLoadingView()
  }

  private fun setupLoadingView() {
    newsLoadingView.speed = 1.25f
  }

  override fun setupTabLayoutViews(tabLayoutViews: List<TabLayoutView>) {
    tabLayout.setupWithViewPager(viewPager)

    for (tabLayoutView in tabLayoutViews) {
      tabLayout.addTab(tabLayout.newTab())
    }

    adapter = NewsPagerAdapter(tabLayoutViews)
    viewPager.adapter = adapter
  }

  inner class NewsPagerAdapter(val tabLayoutViews: List<TabLayoutView>) : PagerAdapter() {

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
