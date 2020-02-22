package com.perelandrax.coinspace.framework.remote.model.coinsnapshot

import com.squareup.moshi.Json

data class SEO(
  @field:Json(name = "PageTitle") var pageTitle: String,
  @field:Json(name = "PageDescription") var pageDescription: String,
  @field:Json(name = "BaseUrl") var baseUrl: String,
  @field:Json(name = "BaseImageUrl") var baseImageUrl: String,
  @field:Json(name = "OgImageUrl") var ogImageUrl: String,
  @field:Json(name = "OgImageWidth") var ogImageWidth: String,
  @field:Json(name = "OgImageHeight") var ogImageHeight: String
)
