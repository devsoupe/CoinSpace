package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

class CryptoCompare(
  @field:Json(name = "SimilarItems") var similarItems: List<SimilarItem>,
  @field:Json(name = "CryptopianFollowers") var cryptopianFollowers: List<CryptopianFollower>,
  @field:Json(name = "Followers") var followers: Int,
  @field:Json(name = "Points") var points: Int,
  @field:Json(name = "Posts") var posts: String,
  @field:Json(name = "Comments") var comments: String,
  @field:Json(name = "PageViewsSplit") var pageViewsSplit: PageViewsSplit,
  @field:Json(name = "PageViews") var pageViews: Int
)
