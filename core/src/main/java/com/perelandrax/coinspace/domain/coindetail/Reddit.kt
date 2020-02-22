package com.perelandrax.coinspace.domain.coindetail

data class Reddit(
  var subscribers: Int = 0,
  var activeUsers: Int = 0,
  var communityCreation: String? = null,
  var postsPerHour: String? = null,
  var postsPerDay: String? = null,
  var commentsPerHour: String? = null,
  var commentsPerDay: Double,
  var link: String? = null,
  var name: String? = null,
  var points: Int = 0
)
