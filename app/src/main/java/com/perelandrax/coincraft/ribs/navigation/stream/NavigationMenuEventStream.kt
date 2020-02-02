package com.perelandrax.coincraft.ribs.navigation.stream

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay
import com.perelandrax.coincraft.R
import com.perelandrax.coincraft.ribs.navigation.stream.NavigationMenuEvent.ABOUT
import com.perelandrax.coincraft.ribs.navigation.stream.NavigationMenuEvent.COINS
import com.perelandrax.coincraft.ribs.navigation.stream.NavigationMenuEvent.ICO

interface NavigationMenuEventStreamSource {
  var event: Relay<NavigationMenuEvent>
}

interface NavigationMenuEventStreamUpdater : NavigationMenuEventStreamSource {
  fun updateMenuId(navigationMenuEvent: NavigationMenuEvent)
}

class NavigationMenuEventStream : NavigationMenuEventStreamUpdater {

  override var event = BehaviorRelay.createDefault(NavigationMenuEvent.COINS).toSerialized()
  override fun updateMenuId(navigationMenuEvent: NavigationMenuEvent) = event.accept(navigationMenuEvent)
}