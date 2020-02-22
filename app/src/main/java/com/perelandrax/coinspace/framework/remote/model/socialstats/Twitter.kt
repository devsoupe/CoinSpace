package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

data class Twitter(
  @field:Json(name = "followers") var followers: Int,
  @field:Json(name = "following") var following: String,
  @field:Json(name = "lists") var lists: Int,
  @field:Json(name = "favourites") var favourites: String,
  @field:Json(name = "statuses") var statuses: Int,
  @field:Json(name = "account_creation") var accountCreation: String,
  @field:Json(name = "name") var name: String,
  @field:Json(name = "link") var link: String,
  @field:Json(name = "Points") var points: Int
)

fun Twitter.mapToDomain(): com.perelandrax.coinspace.domain.coindetail.Twitter =
  com.perelandrax.coinspace.domain.coindetail.Twitter(
    this.followers,
    this.following,
    this.lists,
    this.favourites,
    this.statuses,
    this.accountCreation,
    this.name,
    this.link,
    this.points
  )