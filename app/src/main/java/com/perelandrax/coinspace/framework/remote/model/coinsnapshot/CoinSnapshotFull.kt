package com.perelandrax.coinspace.framework.remote.model.coinsnapshot

import com.squareup.moshi.Json

data class CoinSnapshotFull(
  @field:Json(name = "Response") var response: String,
  @field:Json(name = "Message") var message: String,
  @field:Json(name = "Data") var data: Data,
  @field:Json(name = "Type") var type: Int
)