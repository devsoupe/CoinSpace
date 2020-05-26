package com.perelandrax.coinspace.presentation.ribs.about

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AboutRouterTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var component: AboutBuilder.Component

  @Mock
  internal lateinit var interactor: AboutInteractor

  @Mock
  internal lateinit var view: AboutView

  private lateinit var router: AboutRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = AboutRouter(view, interactor, component)
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

