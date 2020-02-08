package com.perelandrax.coincraft.data.repository

import com.perelandrax.coincraft.data.repository.remote.CoinService
import com.perelandrax.coincraft.data.repository.remote.model.CoinListCmc

/**
 * Created by rendecano on 7/2/18.
 */
class CoinListNetworkRepository(private val coinService: CoinService) {

  suspend fun getCoinListCoinMarketCap(): List<CoinListCmc> {
    return coinService.getCoinListCmc()
  }
}