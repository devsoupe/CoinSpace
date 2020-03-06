package com.perelandrax.coinspace.framework.remote.model.marketcapcoin

import com.squareup.moshi.Json

data class USD(
  @field:Json(name = "price") var price: Double,
  @field:Json(name = "volume_24h") var volume24h: Double,
  @field:Json(name = "percent_change_1h") var percentChange1h: Double,
  @field:Json(name = "percent_change_24h") var percentChange24h: Double,
  @field:Json(name = "percent_change_7d") var percentChange7d: Double,
  @field:Json(name = "market_cap") var market_cap: Double,
  @field:Json(name = "last_updated") var lastUpdated: String
)