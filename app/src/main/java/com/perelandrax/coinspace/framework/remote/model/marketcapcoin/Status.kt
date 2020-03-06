package com.perelandrax.coinspace.framework.remote.model.marketcapcoin

import com.squareup.moshi.Json

data class Status(
  @field:Json(name = "timestamp") var timestamp: String,
  @field:Json(name = "error_code") var errorCode: Int,
  @field:Json(name = "error_message") var errorMessag: String,
  @field:Json(name = "elapsed") var elapsed: Int,
  @field:Json(name = "credit_count") var creditCount: Int
)