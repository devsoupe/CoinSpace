package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

data class Facebook(
  @field:Json(name = "likes") var likes: Int,
  @field:Json(name = "is_closed") var isClosed: String,
  @field:Json(name = "talking_about") var talkingAbout: String,
  @field:Json(name = "name") var name: String,
  @field:Json(name = "link") var link: String,
  @field:Json(name = "Points") var points: Int
)

fun Facebook.mapToDomain(): com.perelandrax.coinspace.domain.coindetail.Facebook =
  com.perelandrax.coinspace.domain.coindetail.Facebook(
    this.likes,
    this.isClosed,
    this.talkingAbout,
    this.name,
    this.link,
    this.points
  )