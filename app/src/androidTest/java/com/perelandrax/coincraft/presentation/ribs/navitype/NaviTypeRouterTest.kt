package com.perelandrax.coincraft.presentation.ribs.navitype

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NaviTypeRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: NaviTypeBuilder.Component
  @Mock internal lateinit var interactor: NaviTypeInteractor

  private var router: NaviTypeRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = NaviTypeRouter(interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}
