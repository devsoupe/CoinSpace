package com.perelandrax.coincraft.ribs.navigation.model.stream

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay

interface NavigationMenuEventStreamSource {
  var event: Relay<NavigationMenuEvent>
}

interface NavigationMenuEventStreamUpdater :
  NavigationMenuEventStreamSource {
  fun updateMenuId(navigationMenuEvent: NavigationMenuEvent)
}

class NavigationMenuEventStream :
  NavigationMenuEventStreamUpdater {

  override var event = BehaviorRelay.createDefault(NavigationMenuEvent.COINS).toSerialized()
  override fun updateMenuId(navigationMenuEvent: NavigationMenuEvent) = event.accept(navigationMenuEvent)
}