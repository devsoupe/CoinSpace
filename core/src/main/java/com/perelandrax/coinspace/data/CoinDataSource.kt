package com.perelandrax.coinspace.data

import com.perelandrax.coinspace.domain.Coin

interface CoinDataSource {

  suspend fun getCoins(): List<Coin>
}