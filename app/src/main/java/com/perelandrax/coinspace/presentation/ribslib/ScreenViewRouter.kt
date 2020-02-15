package com.perelandrax.coinspace.presentation.ribslib

import android.view.View
import com.uber.rib.core.Interactor
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.Router
import com.uber.rib.core.ViewRouter
import com.uber.rib.core.screenstack.lifecycle.ScreenStackEvent

open class ScreenViewRouter<V : View?, I : Interactor<*, *>?, C : InteractorBaseComponent<*>?>(view: V, interactor: I, component: C) :
  ViewRouter<V, I, C>(view, interactor, component) {

  protected fun handleScreenEvents(router: Router<*, *>?, event: ScreenStackEvent?) {
    when (event) {
      ScreenStackEvent.APPEARED -> router?.let { attachChild(it) }
      ScreenStackEvent.HIDDEN, ScreenStackEvent.REMOVED -> router?.let { detachChild(it) }
    }
  }
}
