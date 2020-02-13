package com.perelandrax.coinspace.presentation.ribs.news

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewsRouterTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var component: NewsBuilder.Component
  @Mock
  internal lateinit var interactor: NewsInteractor
  @Mock
  internal lateinit var view: NewsView

  private lateinit var router: NewsRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = NewsRouter(view, interactor, component)
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

