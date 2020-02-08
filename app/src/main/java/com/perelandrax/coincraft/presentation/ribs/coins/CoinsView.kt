package com.perelandrax.coincraft.presentation.ribs.coins

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.perelandrax.coincraft.R
import com.perelandrax.coincraft.presentation.ribs.coins.adapter.CoinListAdapter
import com.perelandrax.coincraft.presentation.ribs.coins.model.CoinListViewModel
import kotlinx.android.synthetic.main.layout_coins_rib.view.loadingView
import kotlinx.android.synthetic.main.layout_coins_rib.view.recyclerView

/**
 * Top level view for {@link CoinsBuilder.CoinsScope}.
 */
class CoinsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  FrameLayout(context, attrs, defStyle), CoinsInteractor.CoinsPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()

    setupLoadingView()
    setupRecyclerView()
  }

  private fun setupLoadingView() {
    loadingView.speed = 1.25f
  }

  private fun setupRecyclerView() {
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = CoinListAdapter(R.layout.recyclerview_coin_list_item)
    recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
  }

  override fun showCoinList(coinList: List<CoinListViewModel>) {
    (recyclerView.adapter as CoinListAdapter).setRepos(coinList)
  }

  override fun showLoading() {
    loadingView.visibility = View.VISIBLE
    loadingView.playAnimation()
  }

  override fun hideLoading() {
    loadingView.visibility = View.GONE
    loadingView.cancelAnimation()
  }
}
