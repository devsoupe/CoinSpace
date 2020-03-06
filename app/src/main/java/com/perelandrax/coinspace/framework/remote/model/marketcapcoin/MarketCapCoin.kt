package com.perelandrax.coinspace.framework.remote.model.marketcapcoin

import com.perelandrax.coinspace.domain.Coin
import com.squareup.moshi.Json

data class MarketCapCoin(
  @field:Json(name = "data") var data: Data,
  @field:Json(name = "status") var status: Status
)

fun MarketCapCoin.mapToDomain(): Coin =
  Coin(
    this.data.id,
    "https://chasing-coins.com/api/v1/std/logo/" + this.data.symbol,
    this.data.symbol,
    this.data.name,
    "${this.data.quote.usd.percentChange24h}%",
    "$${this.data.quote.usd.price}",
    "VOL: ${this.data.quote.usd.volume24h}",
    "this.rank"
  )