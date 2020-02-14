package com.perelandrax.coinspace.framework.remote

import com.perelandrax.coinspace.data.CoinDataSource
import com.perelandrax.coinspace.domain.Coin
import com.perelandrax.coinspace.domain.CoinMaster
import com.perelandrax.coinspace.framework.remote.api.CoinApi
import com.perelandrax.coinspace.framework.remote.model.mapToDomain
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
}
