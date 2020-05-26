package com.perelandrax.coinspace.presentation.ribs

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewsRouterTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var component: IcoBuilder.Component

  @Mock
  internal lateinit var interactor: IcoInteractor

  @Mock
  internal lateinit var view: IcoView

  private lateinit var router: IcoRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = IcoRouter(view, interactor, component)
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

