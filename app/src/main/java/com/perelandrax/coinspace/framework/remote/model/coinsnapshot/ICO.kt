package com.perelandrax.coinspace.framework.remote.model.coinsnapshot

import com.squareup.moshi.Json

class ICO(
  @field:Json(name = "Status") var status: String,
  @field:Json(name = "Description") var description: String,
  @field:Json(name = "TokenType") var tokenType: String,
  @field:Json(name = "Website") var website: String,
  @field:Json(name = "PublicPortfolioUrl") var publicPortfolioUrl: String,
  @field:Json(name = "PublicPortfolioId") var publicPortfolioId: String,
  @field:Json(name = "Features") var features: String,
  @field:Json(name = "FundingTarget") var fundingTarget: String,
  @field:Json(name = "FundingCap") var fundingCap: String,
  @field:Json(name = "ICOTokenSupply") var icoTokenSupply: String,
  @field:Json(name = "TokenSupplyPostICO") var tokenSupplyPostICO: String,
  @field:Json(name = "TokenPercentageForInvestors") var tokenPercentageForInvestors: String,
  @field:Json(name = "TokenReserveSplit") var tokenReserveSplit: String,
  @field:Json(name = "Date") var date: Int,
  @field:Json(name = "EndDate") var endDate: Int,
  @field:Json(name = "FundsRaisedList") var fundsRaisedList: String,
  @field:Json(name = "FundsRaisedUSD") var fundsRaisedUSD: String,
  @field:Json(name = "StartPrice") var startPrice: String,
  @field:Json(name = "StartPriceCurrency") var startPriceCurrency: String,
  @field:Json(name = "PaymentMethod") var paymentMethod: String,
  @field:Json(name = "Jurisdiction") var jurisdiction: String,
  @field:Json(name = "LegalAdvisers") var legalAdvisers: String,
  @field:Json(name = "LegalForm") var legalForm: String,
  @field:Json(name = "SecurityAuditCompany") var securityAuditCompany: String,
  @field:Json(name = "Blog") var blog: String,
  @field:Json(name = "WhitePaper") var whitePaper: String,
  @field:Json(name = "WhitePaperLink") var whitePaperLink: String
)
