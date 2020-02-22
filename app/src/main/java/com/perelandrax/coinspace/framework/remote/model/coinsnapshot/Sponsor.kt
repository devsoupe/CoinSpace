package com.perelandrax.coinspace.framework.remote.model.coinsnapshot

import com.squareup.moshi.Json

class Sponsor(
  @field:Json(name = "TextTop") var textTop: String,
  @field:Json(name = "Link") var link: String,
  @field:Json(name = "ImageUrl") var imageUrl: String
)


