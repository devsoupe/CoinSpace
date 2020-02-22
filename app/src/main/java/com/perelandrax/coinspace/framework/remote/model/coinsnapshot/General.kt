package com.perelandrax.coinspace.framework.remote.model.coinsnapshot

import com.squareup.moshi.Json

data class General(
  @field:Json(name = "Id") var id: String,
  @field:Json(name = "DocumentType") var documentType: String,
  @field:Json(name = "H1Text") var h1Text: String,
  @field:Json(name = "DangerTop") var dangerTop: String,
  @field:Json(name = "WarningTop") var warningTop: String,
  @field:Json(name = "InfoTop") var infoTop: String,
  @field:Json(name = "Symbol") var symbol: String,
  @field:Json(name = "Url") var url: String,
  @field:Json(name = "BaseAngularUrl") var baseAngularUrl: String,
  @field:Json(name = "Name") var name: String,
  @field:Json(name = "ImageUrl") var imageUrl: String,
  @field:Json(name = "Description") var description: String,
  @field:Json(name = "Features") var features: String,
  @field:Json(name = "Technology") var technology: String,
  @field:Json(name = "TotalCoinSupply") var totalCoinSupply: String,
  @field:Json(name = "Algorithm") var algorithm: String,
  @field:Json(name = "ProofType") var proofType: String,
  @field:Json(name = "StartDate") var startDate: String,
  @field:Json(name = "Twitter") var twitter: String,
  @field:Json(name = "AffiliateUrl") var affiliateUrl: String,
  @field:Json(name = "Website") var website: String,
  @field:Json(name = "Sponsor") var sponsor: Sponsor,
  @field:Json(name = "LastBlockExplorerUpdateTS") var lastBlockExplorerUpdateTS: String,
  @field:Json(name = "DifficultyAdjustment") var difficultyAdjustment: Any,
  @field:Json(name = "BlockRewardReduction") var blockRewardReduction: Any,
  @field:Json(name = "BlockNumber") var blockNumber: String,
  @field:Json(name = "BlockTime") var blockTime: String,
  @field:Json(name = "NetHashesPerSecond") var netHashesPerSecond: String,
  @field:Json(name = "TotalCoinsMined") var totalCoinsMined: String,
  @field:Json(name = "PreviousTotalCoinsMined") var previousTotalCoinsMined: String,
  @field:Json(name = "BlockReward") var blockReward: String
)
