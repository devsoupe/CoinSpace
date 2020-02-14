package com.perelandrax.coinspace.presentation.ribs.coins

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.support.v4.widget.refreshes
import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.presentation.ribs.coins.coinlist.CoinListAdapter
import com.perelandrax.coinspace.domain.Coin
import io.reactivex.Observable
import kotlinx.android.synthetic.main.layout_coins_rib.view.*

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
    coinsLoadingView.speed = 1.25f
  }

  private fun setupRecyclerView() {
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = CoinListAdapter(R.layout.recyclerview_coin_list_item)
    recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
  }

  override fun onRefresh(): Observable<Unit> {
    return swipeRefreshLayout.refreshes()
  }

  override fun showLoading() {
    coinsLoadingView.visibility = View.VISIBLE
    coinsLoadingView.playAnimation()
  }

  override fun hideLoading() {
    coinsLoadingView.visibility = View.GONE
    coinsLoadingView.cancelAnimation()
    swipeRefreshLayout.isRefreshing = false
  }

  override fun showError() {

  }

  override fun showCoinList(coinList: List<Coin>) {
    (recyclerView.adapter as CoinListAdapter).setData(coinList)
  }
}