package com.perelandrax.coincraft.ribs.active

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ActiveRouterTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var component: ActiveBuilder.Component
  @Mock
  internal lateinit var interactor: ActiveInteractor
  @Mock
  internal lateinit var view: ActiveView

  private lateinit var router: ActiveRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = ActiveRouter(view, interactor, component)
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

