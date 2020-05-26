package com.perelandrax.coinspace.presentation.ribs.coindetail

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CoinDetailRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: CoinDetailBuilder.Component
  @Mock internal lateinit var interactor: CoinDetailInteractor
  @Mock internal lateinit var view: CoinDetailView

  private lateinit var router: CoinDetailRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = CoinDetailRouter(view, interactor, component)
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

