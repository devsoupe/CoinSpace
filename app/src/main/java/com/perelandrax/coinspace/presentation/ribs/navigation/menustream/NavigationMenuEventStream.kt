package com.perelandrax.coinspace.presentation.ribs.navigation.menustream

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay

interface NavigationMenuEventStreamSource {
  var event: Relay<NavigationMenuEvent>
}

interface NavigationMenuEventStreamUpdater : NavigationMenuEventStreamSource {
  fun updateMenuEvent(event: NavigationMenuEvent)
}

class NavigationMenuEventStream : NavigationMenuEventStreamUpdater {

  override var event = BehaviorRelay.createDefault(NavigationMenuEvent.COINS).toSerialized()

  override fun updateMenuEvent(event: NavigationMenuEvent) = this.event.accept(event)
}