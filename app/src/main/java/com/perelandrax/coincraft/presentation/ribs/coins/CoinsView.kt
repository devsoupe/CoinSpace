package com.perelandrax.coincraft.presentation.ribs.coins

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.support.v4.widget.refreshes
import com.perelandrax.coincraft.R
import com.perelandrax.coincraft.presentation.ribs.coins.coinlist.CoinListAdapter
import com.perelandrax.coincraft.presentation.ribs.coins.coinlist.CoinListViewModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.layout_coins_rib.view.loadingView
import kotlinx.android.synthetic.main.layout_coins_rib.view.recyclerView
import kotlinx.android.synthetic.main.layout_coins_rib.view.swipeRefreshLayout

/**
 * Top level view for {@link CoinsBuilder.CoinsScope}.
 */
class CoinsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), CoinsInteractor.CoinsPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()

    setupRecyclerView()
    setupLoadingView()
  }

  private fun setupLoadingView() {
    loadingView.speed = 1.25f
  }

  private fun setupRecyclerView() {
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = CoinListAdapter(R.layout.recyclerview_coin_list_item)
    recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
  }

  override fun onRefresh(): Observable<Unit> {
    return swipeRefreshLayout.refreshes()
  }

  override fun showCoinList(coinList: List<CoinListViewModel>) {
    (recyclerView.adapter as CoinListAdapter).setData(coinList)
  }

  override fun showLoading() {
    loadingView.visibility = View.VISIBLE
    loadingView.playAnimation()
  }

  override fun hideLoading() {
    loadingView.visibility = View.GONE
    loadingView.cancelAnimation()
    swipeRefreshLayout.isRefreshing = false
  }
}
