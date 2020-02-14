package com.perelandrax.coinspace.data

class CoinRepository(private val dataSource: CoinDataSource) {

  suspend fun getCoinMaster() = dataSource.getCoinMaster()
  suspend fun getCoins() = dataSource.getCoins()
}