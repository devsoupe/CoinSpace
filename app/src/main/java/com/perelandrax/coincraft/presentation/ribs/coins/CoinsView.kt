package com.perelandrax.coincraft.presentation.ribs.coins

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.perelandrax.coincraft.R
import com.perelandrax.coincraft.presentation.ribs.coins.model.CoinListViewModel
import com.perelandrax.coincraft.presentation.ribs.coins.adapter.CoinListAdapter
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

    addDummyData()
  }

  private fun addDummyData() {
    val dummyData = mutableListOf<CoinListViewModel>().apply {
      for (i in 0..10) {
        add(
          CoinListViewModel(
            "$i",
            "",
            "Name $i",
            "CoinName $i",
            "Percent $i",
            "Price $i",
            "Volume $i", ""
          )
        )
      }
    }

    (recyclerView.adapter as CoinListAdapter).setRepos(dummyData)
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
