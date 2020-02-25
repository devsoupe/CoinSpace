package com.perelandrax.coinspace.domain.coindetail

data class Twitter(
  var followers: Int? = null,
  var following: String? = null,
  var lists: Int? = null,
  var favourites: String? = null,
  var statuses: Int? = null,
  var accountCreation: String? = null,
  var name: String? = null,
  var link: String? = null,
  var points: Int? = null
)
