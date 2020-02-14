package com.perelandrax.coinspace.interactors

import com.perelandrax.coinspace.data.CoinRepository

class GetCoinMaster(private val repository: CoinRepository) {

  suspend fun invoke() = repository.getCoinMaster()
}