package com.perelandrax.coincraft.ribs.navigation

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NavigationRouterTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var component: NavigationBuilder.Component
  @Mock
  internal lateinit var interactor: NavigationInteractor
  @Mock
  internal lateinit var view: NavigationView

  private lateinit var router: NavigationRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = NavigationRouter(view, interactor, component)
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

