package com.perelandrax.coinspace.framework.remote.model.marketcapcoin

import com.squareup.moshi.Json

data class Quote(
  @field:Json(name = "USD") var usd: USD
)
