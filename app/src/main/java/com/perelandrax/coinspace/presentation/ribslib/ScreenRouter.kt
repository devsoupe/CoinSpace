package com.perelandrax.coinspace.presentation.ribslib

import com.uber.rib.core.Interactor
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.Router
import com.uber.rib.core.screenstack.lifecycle.ScreenStackEvent

open class ScreenRouter<I : Interactor<*, *>?, C : InteractorBaseComponent<*>?>(interactor: I, component: C) :
  Router<I, C>(interactor, component) {

  protected fun handleScreenEvents(router: Router<*, *>?, event: ScreenStackEvent?) {
    when (event) {
      ScreenStackEvent.APPEARED -> router?.let { attachChild(it) }
      ScreenStackEvent.HIDDEN, ScreenStackEvent.REMOVED -> router?.let { detachChild(it) }
    }
  }
}