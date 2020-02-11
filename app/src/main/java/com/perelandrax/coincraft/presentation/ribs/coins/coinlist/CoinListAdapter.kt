package com.perelandrax.coincraft.presentation.ribs.coins.coinlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.perelandrax.coincraft.R
import kotlinx.android.synthetic.main.recyclerview_coin_list_item.view.*

class CoinListAdapter(private val resId: Int) :
  RecyclerView.Adapter<CoinListAdapter.ViewHolder>() {

  private val items: MutableList<CoinListViewModel> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(resId, parent, false)
    return ViewHolder(parent.context, itemView)
  }

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.showData(items[position])
  }

  fun setData(newItems: List<CoinListViewModel>) {
    this.items.clear()
    this.items.addAll(newItems)

    notifyDataSetChanged()
  }

  inner class ViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val coinLogoImageView = itemView.coinLogoImageView
    private val symbolTextView = itemView.symbolTextView
    private val nameTextView = itemView.nameTextView
    private val percentTextView = itemView.percentTextView
    private val priceTextView = itemView.priceTextView
    private val volumeTextView = itemView.volumeTextView

    fun showData(item: CoinListViewModel) {
      Glide.with(context).load(item.imageUrl).into(coinLogoImageView)

      symbolTextView.text = item.coinName
      nameTextView.text = item.name
      percentTextView.text = item.percentage
      priceTextView.text = item.price
      volumeTextView.text = item.volume

      val percentTextColor = if (percentTextView.text.contains("-")) {
        ContextCompat.getColor(context, R.color.colorPercentageNegative)
      } else {
        ContextCompat.getColor(context, R.color.colorPercentage)
      }

      percentTextView.setTextColor(percentTextColor)
    }
  }
}