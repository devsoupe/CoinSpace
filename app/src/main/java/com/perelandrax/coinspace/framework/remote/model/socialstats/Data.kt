package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

class Data(
  @field:Json(name = "General") var general: General,
  @field:Json(name = "CryptoCompare") var cryptoCompare: CryptoCompare,
  @field:Json(name = "Twitter") var twitter: Twitter,
  @field:Json(name = "Reddit") var reddit: Reddit,
  @field:Json(name = "Facebook") var facebook: Facebook,
  @field:Json(name = "CodeRepository") var codeRepository: CodeRepository
)
