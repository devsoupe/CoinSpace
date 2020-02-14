package com.perelandrax.coinspace.framework.remote.model

import com.squareup.moshi.Json

data class CrytoCompareCoinInfo(
  @field:Json(name = "Response") var response: String,
  @field:Json(name = "Message") var message: String,
  @field:Json(name = "BaseImageUrl") var baseImageUrl: String,
  @field:Json(name = "BaseLinkUrl") var baseLinkUrl: String,
  @field:Json(name = "Data") val feeds: Map<String, CrytoCompareCoin>,
  @field:Json(name = "Type") var type: Int
)