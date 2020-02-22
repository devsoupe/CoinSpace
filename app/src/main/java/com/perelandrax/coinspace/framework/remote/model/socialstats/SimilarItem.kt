package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

class SimilarItem(
  @field:Json(name = "Id") var id: Int,
  @field:Json(name = "Name") var name: String?,
  @field:Json(name = "FullName") var fullName: String?,
  @field:Json(name = "ImageUrl") var imageUrl: String?,
  @field:Json(name = "Url") var url: String?,
  @field:Json(name = "FollowingType") var followingType: Int
)