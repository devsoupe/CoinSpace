package com.perelandrax.coinspace.presentation.ribs.coins

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.support.v4.widget.refreshes
import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.domain.Coin
import com.perelandrax.coinspace.presentation.ribs.coins.coinlist.CoinListAdapter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.layout_coins_rib.view.*
import kotlinx.android.synthetic.main.layout_loading_bar.view.*

/**
 * Top level view for {@link CoinsBuilder.CoinsScope}.
 */
class CoinsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), CoinsInteractor.CoinsPresenter {

  private lateinit var coinListAdapter: CoinListAdapter

  override fun onFinishInflate() {
    super.onFinishInflate()

    setupRecyclerView()
    setupLoadingView()
  }
  private fun setupRecyclerView() {
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = CoinListAdapter(R.layout.recyclerview_coin_list_item).apply { coinListAdapter = this }
    recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
  }

  private fun setupLoadingView() {
    loadingView.speed = 1.5f
  }

  override fun onRefreshCoinList(): Observable<Unit> {
    return swipeRefreshLayout.refreshes()
  }

  override fun onNavigateCoinDetail(): Observable<Coin> {
    return coinListAdapter.getItemClickEvent()
  }

  override fun showLoading() {
    loadingLayout.visibility = View.VISIBLE
    loadingView.playAnimation()
  }

  override fun hideLoading() {
    loadingLayout.visibility = View.GONE
    loadingView.cancelAnimation()
    swipeRefreshLayout.isRefreshing = false
  }

  override fun showError(throwable: Throwable) {
    throwable.printStackTrace()
  }

  override fun showCoinList(coinList: List<Coin>) {
    coinListAdapter.setData(coinList)
  }
}
