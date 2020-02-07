package com.perelandrax.coincraft.ribs.coins.presentation.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.perelandrax.coincraft.ribs.coins.data.viewmodel.CoinListViewModel
import kotlinx.android.synthetic.main.recyclerview_coin_list_item.view.coinLogoImageView
import kotlinx.android.synthetic.main.recyclerview_coin_list_item.view.itemLayout
import kotlinx.android.synthetic.main.recyclerview_coin_list_item.view.nameTextView
import kotlinx.android.synthetic.main.recyclerview_coin_list_item.view.percentTextView
import kotlinx.android.synthetic.main.recyclerview_coin_list_item.view.priceTextView
import kotlinx.android.synthetic.main.recyclerview_coin_list_item.view.symbolTextView
import kotlinx.android.synthetic.main.recyclerview_coin_list_item.view.volumeTextView

class CoinListAdapter(private val resId: Int, val repos: MutableList<CoinListViewModel> = mutableListOf()) : RecyclerView.Adapter<CoinListAdapter.ViewHolder>() {

  private lateinit var context: Context

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    this.context = parent.context
    val itemView = LayoutInflater.from(parent.context).inflate(resId, parent, false)
    return ViewHolder(itemView)
  }

  override fun getItemCount(): Int = repos.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(repos[position])
  }

  fun setRepos(repos: List<CoinListViewModel>) {
    this.repos.clear()
    this.repos.addAll(repos)

    notifyDataSetChanged()
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val coinLogoImageView = itemView.coinLogoImageView
    private val symbolTextView = itemView.symbolTextView
    private val nameTextView = itemView.nameTextView
    private val percentTextView = itemView.percentTextView
    private val priceTextView = itemView.priceTextView
    private val volumeTextView = itemView.volumeTextView

    fun bind(item: CoinListViewModel) {
//      Glide.with(context).load(item.imageUrl).into(coinLogoImageView)

      symbolTextView.text = item.coinName
      nameTextView.text = item.name
      percentTextView.text = item.percentage
      priceTextView.text = item.price
      volumeTextView.text = item.volume
    }
  }
}