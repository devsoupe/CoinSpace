package com.perelandrax.coinspace.data

import com.perelandrax.coinspace.domain.Coin
import com.perelandrax.coinspace.domain.CoinDetail
import com.perelandrax.coinspace.domain.CoinMaster

interface CoinDataSource {

  suspend fun getCoinMaster(): List<CoinMaster>
  suspend fun getCoins(): List<Coin>
  suspend fun getCoinDetail(coinId: String): CoinDetail
}