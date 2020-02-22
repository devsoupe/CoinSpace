package com.perelandrax.coinspace.framework.remote.model.socialstats

import com.squareup.moshi.Json

data class CodeList(
  @field:Json(name = "stars") var stars: Int,
  @field:Json(name = "language") var language: String,
  @field:Json(name = "forks") var forks: Int,
  @field:Json(name = "open_total_issues") var openTotalIssues: String,
  @field:Json(name = "subscribers") var subscribers: Int,
  @field:Json(name = "size") var size: String,
  @field:Json(name = "url") var url: String,
  @field:Json(name = "last_update") var lastUpdate: String,
  @field:Json(name = "last_push") var lastPush: String,
  @field:Json(name = "created_at") var createdAt: String,
  @field:Json(name = "fork") var fork: String,
  @field:Json(name = "source") var source: Source,
  @field:Json(name = "parent") var parent: Parent,
  @field:Json(name = "open_pull_issues") var openPullIssues: String,
  @field:Json(name = "closed_pull_issues") var closedPullIssues: String,
  @field:Json(name = "closed_total_issues") var closedTotalIssues: String,
  @field:Json(name = "open_issues") var openIssues: String,
  @field:Json(name = "closed_issues") var closedIssues: String
)
