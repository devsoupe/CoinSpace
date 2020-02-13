package com.perelandrax.coinspace.data

class CoinRepository(private val dataSource: CoinDataSource) {

  suspend fun getCoins() = dataSource.getCoins()
}