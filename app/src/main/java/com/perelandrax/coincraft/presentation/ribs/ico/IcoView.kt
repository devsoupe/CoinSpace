package com.perelandrax.coincraft.presentation.ribs.ico

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewpager.widget.PagerAdapter
import com.perelandrax.coincraft.R
import kotlinx.android.synthetic.main.coins_rib.view.loadingView
import kotlinx.android.synthetic.main.ico_rib.view.tabLayout
import kotlinx.android.synthetic.main.ico_rib.view.viewPager

/**
 * Top level view for {@link IcoBuilder.IcoScope}.
 */
class IcoView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), IcoInteractor.IcoPresenter {

  private lateinit var adapter: IcoPagerAdapter

  override fun onFinishInflate() {
    super.onFinishInflate()

    setupTabViewPagerLayout()
    setupLoadingView()
  }

  private fun setupTabViewPagerLayout() {
    tabLayout.setupWithViewPager(viewPager)
    tabLayout.addTab(tabLayout.newTab())
    tabLayout.addTab(tabLayout.newTab())

    adapter = IcoPagerAdapter(tabLayout.tabCount)
    viewPager.adapter = adapter
  }

  private fun setupLoadingView() {
    loadingView.speed = 1.25f
  }

  inner class IcoPagerAdapter(val tabCount: Int) : PagerAdapter() {

//    override fun instantiateItem(: ViewGroup, position: Int): Any {
//      val res = if (position == 0) R.layout.active_rib else R.layout.upcoming_rib
//      val inflater = LayoutInflater.from(context)
//      val layout = inflater.inflate(res, collection, false)
////      layout.setItems(listIcoItems[position])
////
////      layout.onSelectItem().subscribe({ iconItem ->
////        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(iconItem.website))
////        context.startActivity(browserIntent)
////      })
////
//      collection.addView(layout)
//      return layout
//    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
      val res = if (position == 0) R.layout.active_rib else R.layout.upcoming_rib
      val inflater = LayoutInflater.from(context)
      val view = inflater.inflate(res, container, false)

      container.addView(view)
      return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
      container.removeView(view as View)
    }

    override fun getCount(): Int {
      return tabCount
    }

    override fun getPageTitle(position: Int): CharSequence? {
      if (position == 0) {
        return "ACTIVE"
      } else {
        return "UPCOMING"
      }
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
      return view == obj
    }
  }
}
