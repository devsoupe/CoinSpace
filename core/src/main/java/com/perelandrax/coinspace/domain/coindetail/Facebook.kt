package com.perelandrax.coinspace.domain.coindetail

data class Facebook(
  var likes: Int = 0,
  var isClosed: String? = null,
  var talkingAbout: String? = null,
  var name: String? = null,
  var link: String? = null,
  var points: Int = 0
)
