package com.perelandrax.coincraft.data.repository.remote

import com.perelandrax.coincraft.data.repository.remote.model.CoinListCmc
import okhttp3.OkHttpClient

class CoinService(val okHttpClient: OkHttpClient) {

  companion object {

    private const val COIN_MARKET_CAP = "https://api.coinmarketcap.com/"
  }

  suspend fun getCoinListCmc(): List<CoinListCmc> {
    val coinApi = CoinApi.create(COIN_MARKET_CAP, okHttpClient)
    return coinApi.getCoinListCmc()
  }
}