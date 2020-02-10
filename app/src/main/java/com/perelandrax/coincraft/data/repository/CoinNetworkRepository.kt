package com.perelandrax.coincraft.data.repository

import com.perelandrax.coincraft.data.repository.remote.CoinService
import com.perelandrax.coincraft.data.repository.remote.model.CoinModel

/**
 * Created by rendecano on 7/2/18.
 */
class CoinNetworkRepository(private val coinService: CoinService) {

  suspend fun getCoinListCoinMarketCap(): List<CoinModel> {
    return coinService.getCoinListCmc()
  }
}