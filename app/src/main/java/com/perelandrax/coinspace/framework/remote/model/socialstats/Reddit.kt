package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

data class Reddit(
  @field:Json(name = "subscribers") var subscribers: Int,
  @field:Json(name = "active_users") var activeUsers: Int,
  @field:Json(name = "community_creation") var communityCreation: String,
  @field:Json(name = "posts_per_hour") var postsPerHour: String,
  @field:Json(name = "posts_per_day") var postsPerDay: String,
  @field:Json(name = "comments_per_hour") var commentsPerHour: String,
  @field:Json(name = "comments_per_day") var commentsPerDay: Double,
  @field:Json(name = "link") var link: String,
  @field:Json(name = "name") var name: String,
  @field:Json(name = "Points") var points: Int
)

fun Reddit.mapToDomain(): com.perelandrax.coinspace.domain.coindetail.Reddit =
  com.perelandrax.coinspace.domain.coindetail.Reddit(
    this.subscribers,
    this.activeUsers,
    this.communityCreation,
    this.postsPerHour,
    this.postsPerDay,
    this.commentsPerHour,
    this.commentsPerDay,
    this.link,
    this.name,
    this.points
  )