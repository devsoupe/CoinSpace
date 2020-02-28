package com.perelandrax.coinspace.interactors

import com.perelandrax.coinspace.data.CoinRepository
import kotlinx.coroutines.CoroutineScope

class GetCoinDetail(private val scope: CoroutineScope,
                    private val coinId: String,
                    private val repository: CoinRepository) {

  suspend fun invoke() = repository.getCoinDetail(scope, coinId)
}