package com.perelandrax.coinspace.framework.remote.model

import com.perelandrax.coinspace.domain.Coin
import com.squareup.moshi.Json

data class MarketCapCoin(
//  @Id(assignable = true) var coinId: Long? = 0,
  @field:Json(name = "id") var id: String,
  @field:Json(name = "name") var name: String,
  @field:Json(name = "symbol") var symbol: String,
  @field:Json(name = "rank") var rank: String,
  @field:Json(name = "price_usd") var priceUsd: String,
  @field:Json(name = "price_btc") var priceBtc: String,
  @field:Json(name = "24h_volume_usd") var _24hVolumeUsd: String,
  @field:Json(name = "market_cap_usd") var marketCapUsd: String,
  @field:Json(name = "available_supply") var availableSupply: String,
  @field:Json(name = "total_supply") var totalSupply: String,
  @field:Json(name = "max_supply") var maxSupply: String,
  @field:Json(name = "percent_change_1h") var percentChange1h: String,
  @field:Json(name = "percent_change_24h") var percentChange24h: String,
  @field:Json(name = "percent_change_7d") var percentChange7d: String,
  @field:Json(name = "last_updated") var lastUpdated: String
)

fun MarketCapCoin.mapToDomain(): Coin =
  Coin(
    this.id,
    "https://chasing-coins.com/api/v1/std/logo/" + this.symbol,
    this.symbol,
    this.name,
    "${this.percentChange24h}%",
    "$${this.priceUsd}",
    "VOL: ${this._24hVolumeUsd}",
    "this.rank"
  )