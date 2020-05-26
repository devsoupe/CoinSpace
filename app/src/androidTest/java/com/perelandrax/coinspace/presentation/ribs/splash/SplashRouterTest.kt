package com.perelandrax.coinspace.presentation.ribs.splash

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SplashRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: SplashBuilder.Component
  @Mock internal lateinit var interactor: SplashInteractor
  @Mock internal lateinit var view: SplashView

  private lateinit var router: SplashRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = SplashRouter(view, interactor, component)
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

