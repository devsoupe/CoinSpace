package com.perelandrax.coinspace.framework.remote.model.marketcapcoin

import com.squareup.moshi.Json

data class MarketCapCoin(
  @field:Json(name = "data") var data: List<Data>,
  @field:Json(name = "status") var status: Status
)