package com.perelandrax.coinspace.domain.coindetail

data class Twitter(
  var followers: Int = 0,
  var following: String? = null,
  var lists: Int = 0,
  var favourites: String? = null,
  var statuses: Int = 0,
  var accountCreation: String? = null,
  var name: String? = null,
  var link: String? = null,
  var points: Int = 0
)
