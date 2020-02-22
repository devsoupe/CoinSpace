package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

class General(
  @field:Json(name = "Name") var name: String,
  @field:Json(name = "CoinName") var coinName: String,
  @field:Json(name = "Type") var type: String,
  @field:Json(name = "Points") var points: Int
)
