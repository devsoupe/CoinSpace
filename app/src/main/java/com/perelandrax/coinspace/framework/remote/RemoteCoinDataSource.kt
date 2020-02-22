package com.perelandrax.coinspace.framework.remote

import com.perelandrax.coinspace.data.CoinDataSource
import com.perelandrax.coinspace.domain.Coin
import com.perelandrax.coinspace.domain.coindetail.CoinDetail
import com.perelandrax.coinspace.domain.CoinMaster
import com.perelandrax.coinspace.framework.remote.api.CoinApi
import com.perelandrax.coinspace.framework.remote.model.crytocompare.mapToDomain
import com.perelandrax.coinspace.framework.remote.model.mapToDomain
import com.perelandrax.coinspace.framework.remote.model.socialstats.mapToDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import okhttp3.OkHttpClient

class RemoteCoinDataSource(private val okHttpClient: OkHttpClient) : CoinDataSource {

  companion object {

    private const val COIN_MARKET_CAP = "https://api.coinmarketcap.com/"
    private const val CRYPTO_COMPARE = "https://www.cryptocompare.com/"
    private const val CRYPTO_COMPARE_MIN_API = "https://min-api.cryptocompare.com/"
  }

  override suspend fun getCoinMaster(): List<CoinMaster> {
    val coinApi = CoinApi.create(CRYPTO_COMPARE_MIN_API, okHttpClient)

    return mutableListOf<CoinMaster>().apply {
      val iterator = coinApi.getCrytoCompareCoinList().feeds.iterator()
      while (iterator.hasNext()) {
        add(iterator.next().value.mapToDomain())
      }
    }
  }

  override suspend fun getCoins(): List<Coin> {
    val coinApi = CoinApi.create(COIN_MARKET_CAP, okHttpClient)

    return mutableListOf<Coin>().apply {
      coinApi.getMarketCapCoinList().forEach {
        add(it.mapToDomain())
      }
    }
  }

  override suspend fun getCoinDetail(scope: CoroutineScope, coinId: String): CoinDetail = scope.run {
    val coinApi = CoinApi.create(CRYPTO_COMPARE, okHttpClient)

    val snapShotFullDeferred = async { coinApi.getCoinSnapshotFull(coinId) }
    val socialStatsDeferred = async { coinApi.getSocialStats(coinId) }

    val snapShotFull = snapShotFullDeferred.await()
    val socialStats = socialStatsDeferred.await()

    val baseUrl = snapShotFull.data.seo.baseImageUrl
    val general = snapShotFull.data.general
    val ico = snapShotFull.data.ico
    val socialStatsRepo = socialStats.data?.codeRepository

    CoinDetail(
      general.id,
      general.name,
      general.symbol,
      general.description,
      general.technology,
      general.features,
      baseUrl + general.imageUrl,
      general.totalCoinSupply,
      general.startDate,
      if (!general.affiliateUrl.isNullOrEmpty() && general.affiliateUrl!!.contains("http")) general.affiliateUrl else "",
      baseUrl + general.sponsor?.imageUrl,
      ico.status,
      ico.description,
      ico.icoTokenSupply,
      ico.fundingTarget,
      ico.whitePaperLink,
      socialStats.data?.twitter.mapToDomain(),
      socialStats.data?.reddit.mapToDomain(),
      socialStats.data?.facebook.mapToDomain()
//      if (!socialStatsRepo?.codeList.orEmpty().isEmpty()) socialStatsRepo?.codeList?.get(0) else CodeList()
    )
  }
}
