package com.perelandrax.coinspace.presentation.ribs.splash.masterstream

import com.jakewharton.rxrelay2.BehaviorRelay
import com.perelandrax.coinspace.domain.CoinMaster

interface CoinMasterStreamSource {
  var source: BehaviorRelay<List<CoinMaster>>
}

interface CoinMasterStreamUpdater : CoinMasterStreamSource {
  fun updateSource(value: List<CoinMaster>)
}

class CoinMasterStream : CoinMasterStreamUpdater {

  override var source = BehaviorRelay.createDefault<List<CoinMaster>>(emptyList())

  override fun updateSource(value: List<CoinMaster>) = this.source.accept(value)
}