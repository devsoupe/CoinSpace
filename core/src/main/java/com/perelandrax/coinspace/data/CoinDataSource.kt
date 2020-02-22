package com.perelandrax.coinspace.data

import com.perelandrax.coinspace.domain.Coin
import com.perelandrax.coinspace.domain.coindetail.CoinDetail
import com.perelandrax.coinspace.domain.CoinMaster
import kotlinx.coroutines.CoroutineScope

interface CoinDataSource {

  suspend fun getCoinMaster(): List<CoinMaster>
  suspend fun getCoins(): List<Coin>
  suspend fun getCoinDetail(scope: CoroutineScope, coinId: String): CoinDetail
}