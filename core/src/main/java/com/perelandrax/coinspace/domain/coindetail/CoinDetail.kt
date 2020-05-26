package com.perelandrax.coinspace.domain.coindetail

data class CoinDetail(
  var coinId: String? = null,
  var name: String? = null,
  var symbol: String? = null,
  var description: String? = null,
  var technology: String? = null,
  var features: String? = null,
  var imageUrl: String? = null,
  var totalCoinSupply: String? = null,
  var startDate: String? = null,
  var website: String? = null,
  var icoStatus: String? = null,
  var icoDescription: String? = null,
  var icoTokenSupply: String? = null,
  var fundingTarget: String? = null,
  var icoWhitePaperUrl: String? = null,
  var twitterObj: Twitter? = null,
  var reddit: Reddit? = null,
  var facebook: Facebook? = null
//  var codeList: CodeList,
)