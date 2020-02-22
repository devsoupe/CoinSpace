package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

class CodeRepository(
  @field:Json(name = "List") var codeList: List<CodeList>,
  @field:Json(name = "Points") var points: Int = 0
)