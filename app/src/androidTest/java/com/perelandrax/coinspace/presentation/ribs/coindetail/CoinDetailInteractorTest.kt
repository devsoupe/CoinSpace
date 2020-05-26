package com.perelandrax.coinspace.presentation.ribs.coindetail

import com.uber.rib.core.InteractorHelper
import com.uber.rib.core.RibTestBasePlaceholder
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CoinDetailInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: CoinDetailInteractor.CoinDetailPresenter
  @Mock internal lateinit var listener: CoinDetailInteractor.Listener
  @Mock internal lateinit var router: CoinDetailRouter

  private lateinit var interactor: CoinDetailInteractor

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestCoinDetailInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun `an example test`() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<CoinDetailInteractor.CoinDetailPresenter, CoinDetailRouter>(interactor, presenter, router, null)
    InteractorHelper.detach(interactor)

    throw RuntimeException("Remove this test and add real tests.")
  }
}