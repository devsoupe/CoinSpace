package com.perelandrax.coincraft.data.api

import com.perelandrax.coincraft.data.model.CoinMarketCap
import okhttp3.OkHttpClient

class CoinService(val okHttpClient: OkHttpClient) {

  companion object {

    private const val COIN_MARKET_CAP = "https://api.coinmarketcap.com/"
  }

  suspend fun getCoinMarketCapList(): List<CoinMarketCap> {
    val coinApi = CoinApi.create(COIN_MARKET_CAP, okHttpClient)
    return coinApi.getCoinMarketCapList()
  }
}