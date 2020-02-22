package com.perelandrax.coinspace.domain

data class Coin(
  val id: String? = null,
  val imageUrl: String? = null,
  val name: String? = null,
  val coinName: String? = null,
  val percentage: String? = null,
  val price: String? = null,
  val volume: String? = null,
  val sortOrder: String? = null,
  var detailId: String? = null
)