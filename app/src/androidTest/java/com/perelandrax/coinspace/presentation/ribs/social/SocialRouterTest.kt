package com.perelandrax.coinspace.presentation.ribs.social

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SocialRouterTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var component: SocialBuilder.Component

  @Mock
  internal lateinit var interactor: SocialInteractor

  @Mock
  internal lateinit var view: SocialView

  private lateinit var router: SocialRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = SocialRouter(view, interactor, component)
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

