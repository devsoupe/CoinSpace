package com.perelandrax.coinspace.domain

data class CoinDetail(
  var coinId: String,
  var name: String,
  var symbol: String,
  var description: String,
  var technology: String,
  var features: String,
  var imageUrl: String,
  var totalCoinSupply: String,
  var startDate: String,
  var website: String,
  var sponsorImageUrl: String,
  var icoStatus: String,
  var icoDescription: String,
  var icoTokenSupply: String,
  var fundingTarget: String,
  var icoWhitePaperUrl: String
//  var twitterObj: Twitter? = null,
//  var reddit: Reddit? = null,
//  var facebook: Facebook? = null,
//  var codeList: CodeList? = null
)