package com.perelandrax.coinspace.framework.remote.model.marketcapcoin

import com.squareup.moshi.Json

data class Data(
  @field:Json(name = "id") var id: String,
  @field:Json(name = "name") var name: String,
  @field:Json(name = "symbol") var symbol: String,
  @field:Json(name = "slug") var slug: String,
  @field:Json(name = "circulating_supply") var circulatingSupply: Int,
  @field:Json(name = "total_supply") var totalSupply: Int,
  @field:Json(name = "max_supply") var maxSupply: Int,
  @field:Json(name = "date_added") var dateAdded: String,
  @field:Json(name = "num_market_pairs") var numMarketPairs: Int,
  @field:Json(name = "cmc_rank") var cmcRank: Int,
  @field:Json(name = "last_updated") var lastUpdated: String,
  @field:Json(name = "tags") var tags: List<String>,
  @field:Json(name = "platform") var platform: Any,
  @field:Json(name = "quote") var quote: Quote
)