package com.perelandrax.coincraft.ribs.coins

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CoinsRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: CoinsBuilder.Component
  @Mock internal lateinit var interactor: CoinsInteractor
  @Mock internal lateinit var view: CoinsView

  private lateinit var router: CoinsRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = CoinsRouter(view, interactor, component)
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

