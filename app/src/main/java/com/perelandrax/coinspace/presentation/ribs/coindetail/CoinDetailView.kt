package com.perelandrax.coinspace.presentation.ribs.coindetail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Html
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.transition.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.jakewharton.rxbinding2.view.clicks
import com.orhanobut.logger.Logger
import com.perelandrax.coinspace.R
import com.perelandrax.coinspace.domain.CoinWebsite
import com.perelandrax.coinspace.domain.coindetail.CoinDetail
import com.perelandrax.coinspace.presentation.screenstack.AnimationFrameLayout
import io.reactivex.Observable
import kotlinx.android.synthetic.main.layout_coin_detail_features.view.*
import kotlinx.android.synthetic.main.layout_coin_detail_info.view.*
import kotlinx.android.synthetic.main.layout_coin_detail_rib.view.*
import kotlinx.android.synthetic.main.layout_loading_bar.view.*
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Top level view for {@link CoinDetailBuilder.CoinDetailScope}.
 */
class CoinDetailView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  AnimationFrameLayout(context, attrs, defStyle), CoinDetailInteractor.CoinDetailPresenter {

  private lateinit var coinDetail: CoinDetail
  private var coinColor = Color.LTGRAY

  override fun onFinishInflate() {
    super.onFinishInflate()
    setupLoadingView()
  }

  private fun setupLoadingView() {
    loadingView.speed = 1.25f
  }

  override fun onNavigateWebsite(): Observable<CoinWebsite> {
    return websiteButton.clicks()
      .throttleFirst(1000, TimeUnit.MILLISECONDS)
      .map { CoinWebsite("${coinDetail.symbol} (${coinDetail.name})", coinColor, coinDetail.website) }
  }

  override fun showLoading() {
    loadingLayout.visibility = View.VISIBLE
    loadingView.playAnimation()
  }

  override fun hideLoading() {
    loadingLayout.visibility = View.GONE
    loadingView.cancelAnimation()
  }

  override fun showError(throwable: Throwable) {
    Logger.e("CoinDetailView : showError : $throwable")
  }

  override fun showCoinDetail(coinDetail: CoinDetail) {
    this.coinDetail = coinDetail

    TransitionManager.beginDelayedTransition(coinDetailFeaturesLayout, AutoTransition())

    nameTextView.text = coinDetail.name
    symbolTextView.text = coinDetail.symbol

    TransitionManager.beginDelayedTransition(coinDetailInfoLayout, AutoTransition())

    descriptionTextView.text = Html.fromHtml(coinDetail.description)
    featuresTextView.text = Html.fromHtml(coinDetail.features)
    coinSupplyTextView.text = coinDetail.totalCoinSupply
    startDateTextView.text = coinDetail.startDate

    if (coinDetail.website.isNotEmpty()) {
      websiteCardView.visibility = View.VISIBLE
    }

    Glide.with(context)
      .asBitmap()
      .load(coinDetail.imageUrl)
      .into(object : CustomTarget<Bitmap>(96, 96) {

        override fun onLoadCleared(placeholder: Drawable?) { }
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
          coinLogoImageView.setImageBitmap(resource)
          dominantBgView.setBackgroundColor(getDominantColor(resource).apply { coinColor = this })
          websiteButton.setBackgroundColor(coinColor)
        }
      })
  }

  private fun getDominantColor(bitmap: Bitmap): Int {
    val swatchesTemp = Palette.from(bitmap).generate().swatches
    val swatches = ArrayList(swatchesTemp)

    swatches.sortWith(Comparator { swatch1, swatch2 -> swatch2.population - swatch1.population })

    return if (swatches.size > 0) swatches[0].rgb else ContextCompat.getColor(context, R.color.colorPrimary)
  }
}
