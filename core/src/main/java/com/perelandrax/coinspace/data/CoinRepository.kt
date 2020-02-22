package com.perelandrax.coinspace.data

import kotlinx.coroutines.CoroutineScope

class CoinRepository(private val dataSource: CoinDataSource) {

  suspend fun getCoinMaster() = dataSource.getCoinMaster()
  suspend fun getCoins() = dataSource.getCoins()
  suspend fun getCoinDetail(scope: CoroutineScope, coinId: String) = dataSource.getCoinDetail(scope, coinId)
}