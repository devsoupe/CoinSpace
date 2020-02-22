package com.perelandrax.coinspace.presentation.ribs.coindetail

import android.content.Context
import android.graphics.Bitmap
import android.text.Html
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.orhanobut.logger.Logger
import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.domain.coindetail.CoinDetail
import com.perelandrax.coinspace.presentation.ribslib.ObjectAnimatorFrameLayout
import kotlinx.android.synthetic.main.layout_coin_detail_features.view.*
import kotlinx.android.synthetic.main.layout_coin_detail_info.view.*
import kotlinx.android.synthetic.main.layout_coin_detail_rib.view.*
import java.util.*


/**
 * Top level view for {@link CoinDetailBuilder.CoinDetailScope}.
 */
class CoinDetailView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  ObjectAnimatorFrameLayout(context, attrs, defStyle), CoinDetailInteractor.CoinDetailPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()

    setupLoadingView()
  }

  private fun setupLoadingView() {
    coinDetailLoadingView.speed = 1.25f
  }

  override fun showLoading() {
    coinDetailLoadingView.visibility = View.VISIBLE
    coinDetailLoadingView.playAnimation()
  }

  override fun hideLoading() {
    coinDetailLoadingView.visibility = View.GONE
    coinDetailLoadingView.cancelAnimation()
  }

  override fun showError(throwable: Throwable) {
    Logger.e("CoinDetailView : showError : $throwable")
  }

  override fun showCoinDetail(coinDetail: CoinDetail) {
    coinDetailLayout.visibility = View.VISIBLE

    symbolTextView.text = coinDetail.symbol
    nameTextView.text = coinDetail.name
    descriptionTextView.text = Html.fromHtml(coinDetail.description)
    featuresTextView.text = Html.fromHtml(coinDetail.features)
    coinSupplyTextView.text = coinDetail.totalCoinSupply
    startDateTextView.text = coinDetail.startDate

    if (coinDetail.website.isNullOrEmpty()) {
      websiteButton.visibility = View.GONE
    }

    if (coinDetail.twitterObj?.link.isNullOrEmpty()) {
      twitterButton.visibility = View.GONE
    }

    try {
      Glide.with(context)
        .asBitmap()
        .load(coinDetail.imageUrl)
        .into(object : SimpleTarget<Bitmap>(96, 96) {
          override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            coinLogoImageView.setImageBitmap(resource)
            bg_dominant.setBackgroundColor(getDominantColor(resource))
          }
        })
    } catch (e: Exception) {
      // No implementation
    }
  }

  private fun getDominantColor(bitmap: Bitmap): Int {
    val swatchesTemp = Palette.from(bitmap).generate().swatches
    val swatches = ArrayList(swatchesTemp)

    swatches.sortWith(Comparator { swatch1, swatch2 -> swatch2.population - swatch1.population })

    return if (swatches.size > 0) swatches[0].rgb else ContextCompat.getColor(context, R.color.colorPrimary)
  }
}
