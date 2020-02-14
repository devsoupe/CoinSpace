package com.perelandrax.coinspace.framework.remote.model

import com.perelandrax.coinspace.domain.CoinMaster
import com.squareup.moshi.Json

data class CrytoCompareCoin(
  @field:Json(name = "Id") var id: String,
  @field:Json(name = "Url") var url: String,
  @field:Json(name = "ImageUrl") var imageUrl: String,
  @field:Json(name = "Name") var name: String,
  @field:Json(name = "CoinName") var coinName: String,
  @field:Json(name = "FullName") var fullName: String,
  @field:Json(name = "Algorithm") var algorithm: String,
  @field:Json(name = "ProofType") var proofType: String,
  @field:Json(name = "SortOrder") var sortOrder: String
)

fun CrytoCompareCoin.mapToDomain(): CoinMaster = CoinMaster(this.id, this.name)