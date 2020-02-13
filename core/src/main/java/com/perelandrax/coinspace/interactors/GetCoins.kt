package com.perelandrax.coinspace.interactors

import com.perelandrax.coinspace.data.CoinRepository

class GetCoins(private val repository: CoinRepository) {

  suspend fun invoke() = repository.getCoins()
}