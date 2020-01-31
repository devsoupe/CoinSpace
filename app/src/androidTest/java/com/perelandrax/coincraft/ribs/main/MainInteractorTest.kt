package com.perelandrax.coincraft.ribs.main

import com.uber.rib.core.InteractorHelper
import com.uber.rib.core.RibTestBasePlaceholder
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: MainInteractor.MainPresenter
  @Mock internal lateinit var listener: MainInteractor.Listener
  @Mock internal lateinit var router: MainRouter

  private lateinit var interactor: MainInteractor

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestMainInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun `an example test`() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<MainInteractor.MainPresenter, MainRouter>(interactor, presenter, router, null)
    InteractorHelper.detach(interactor)

    throw RuntimeException("Remove this test and add real tests.")
  }
}