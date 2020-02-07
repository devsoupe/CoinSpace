package com.perelandrax.coincraft.presentation.ribs.main

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainRouterTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var component: MainBuilder.Component
  @Mock
  internal lateinit var interactor: MainInteractor
  @Mock
  internal lateinit var view: MainView

  private lateinit var router: MainRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = MainRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun `an example test`() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router)
    RouterHelper.detach(router)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

