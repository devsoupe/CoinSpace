package com.perelandrax.coincraft.domain.repository

import com.perelandrax.coincraft.data.api.CoinService
import com.perelandrax.coincraft.data.model.CoinMarketCap

class CoinListNetworkRepository(private val coinService: CoinService) {

  suspend fun getCoinMarketCapList(): List<CoinMarketCap> {
    return coinService.getCoinMarketCapList()
  }
}