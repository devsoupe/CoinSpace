package com.perelandrax.coincraft.presentation.ribs.upcoming

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UpcomingRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: UpcomingBuilder.Component
  @Mock internal lateinit var interactor: UpcomingInteractor
  @Mock internal lateinit var view: UpcomingView

  private lateinit var router: UpcomingRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = UpcomingRouter(view, interactor, component)
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

