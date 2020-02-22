package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

class PageViewsSplit(
  @field:Json(name = "Overview") var overview: Int,
  @field:Json(name = "Markets") var markets: Int,
  @field:Json(name = "Analysis") var analysis: Int,
  @field:Json(name = "Charts") var charts: Int,
  @field:Json(name = "Trades") var trades: Int,
  @field:Json(name = "Orderbook") var orderbook: Int,
  @field:Json(name = "Forum") var forum: Int,
  @field:Json(name = "Influence") var influence: Int
)
