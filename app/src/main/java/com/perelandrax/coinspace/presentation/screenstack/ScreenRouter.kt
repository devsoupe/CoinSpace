package com.perelandrax.coinspace.presentation.screenstack

import com.uber.rib.core.Interactor
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.Router
import com.uber.rib.core.screenstack.lifecycle.ScreenStackEvent

open class ScreenRouter<I : Interactor<*, *>?, C : InteractorBaseComponent<*>?>(interactor: I, component: C) :
  Router<I, C>(interactor, component) {

  protected fun handleScreenEvents(router: Router<*, *>?, event: ScreenStackEvent?) {
    when (event) {
      ScreenStackEvent.APPEARED -> router?.let { if (!it.interactor.isAttached) { attachChild(it) } }
      ScreenStackEvent.HIDDEN -> router?.let { }
      ScreenStackEvent.REMOVED -> router?.let { detachChild(it) }
    }
  }
}