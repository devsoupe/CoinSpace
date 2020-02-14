package com.perelandrax.coinspace.domain

data class Coin(
  val id: String,
  val imageUrl: String,
  val name: String,
  val coinName: String,
  val percentage: String,
  val price: String,
  val volume: String,
  val sortOrder: String,
  var detailId: String = ""
)