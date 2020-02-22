package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

data class SocialStats(
  @field:Json(name = "Response") var response: String,
  @field:Json(name = "Message") var message: String,
  @field:Json(name = "Type") var type: Int,
  @field:Json(name = "Data") var data: Data
)