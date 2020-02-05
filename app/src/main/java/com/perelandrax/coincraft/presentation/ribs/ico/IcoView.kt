package com.perelandrax.coincraft.presentation.ribs.ico

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewpager.widget.PagerAdapter
import com.perelandrax.coincraft.presentation.ribs.active.ActiveView
import com.perelandrax.coincraft.presentation.ribs.upcoming.UpcomingView
import kotlinx.android.synthetic.main.coins_rib.view.loadingView
import kotlinx.android.synthetic.main.ico_rib.view.tabLayout
import kotlinx.android.synthetic.main.ico_rib.view.viewPager

/**
 * Top level view for {@link IcoBuilder.IcoScope}.
 */
class IcoView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), IcoInteractor.IcoPresenter {

  private lateinit var adapter: IcoPagerAdapter

  override fun onFinishInflate() {
    super.onFinishInflate()
    setupLoadingView()
  }

  private fun setupLoadingView() {
    loadingView.speed = 1.25f
  }

  override fun setupTabLayoutAndViewPager(
    activeView: ActiveView,
    upcomingView: UpcomingView
  ) {
    tabLayout.setupWithViewPager(viewPager)
    tabLayout.addTab(tabLayout.newTab())
    tabLayout.addTab(tabLayout.newTab())

    adapter = IcoPagerAdapter(tabLayout.tabCount, activeView, upcomingView)
    viewPager.adapter = adapter
  }

  inner class IcoPagerAdapter(
    val tabCount: Int,
    val activeView: ActiveView,
    val upcomingView: UpcomingView
  ) : PagerAdapter() {

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

    override fun instantiateItem(
      container: ViewGroup,
      position: Int
    ): Any {
      return when (position) {
        0 -> activeView
        else -> upcomingView
      }.apply {
        container.addView(this)
      }
    }

    override fun destroyItem(
      container: ViewGroup,
      position: Int,
      view: Any
    ) {
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

    override fun isViewFromObject(
      view: View,
      obj: Any
    ): Boolean {
      return view == obj
    }
  }
}
