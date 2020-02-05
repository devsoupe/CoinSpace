package com.perelandrax.coincraft.presentation.ribs.upcoming

import com.uber.rib.core.InteractorHelper
import com.uber.rib.core.RibTestBasePlaceholder
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UpcomingInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: UpcomingInteractor.UpcomingPresenter
  @Mock internal lateinit var listener: UpcomingInteractor.Listener
  @Mock internal lateinit var router: UpcomingRouter

  private lateinit var interactor: UpcomingInteractor

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestUpcomingInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun `an example test`() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<UpcomingInteractor.UpcomingPresenter, UpcomingRouter>(interactor, presenter, router, null)
    InteractorHelper.detach(interactor)

    throw RuntimeException("Remove this test and add real tests.")
  }
}