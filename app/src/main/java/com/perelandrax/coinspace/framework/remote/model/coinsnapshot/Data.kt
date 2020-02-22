package com.perelandrax.coinspace.framework.remote.model.coinsnapshot

import com.squareup.moshi.Json

data class Data(
  @field:Json(name = "SEO") var seo: SEO,
  @field:Json(name = "General") var general: General,
  @field:Json(name = "ICO") var ico: ICO,
  @field:Json(name = "Subs") var subs: List<String>,
  @field:Json(name = "StreamerDataRaw") var streamerDataRaw: List<String>
)
