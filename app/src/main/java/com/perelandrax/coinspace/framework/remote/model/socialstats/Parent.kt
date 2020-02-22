package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

data class Parent(
  @field:Json(name = "Name") var name: String,
  @field:Json(name = "Url") var url: String,
  @field:Json(name = "InternalId") var internalId: Int
)
