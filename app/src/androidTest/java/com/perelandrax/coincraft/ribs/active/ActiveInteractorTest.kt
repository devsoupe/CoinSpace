package com.perelandrax.coincraft.ribs.active

import com.uber.rib.core.InteractorHelper
import com.uber.rib.core.RibTestBasePlaceholder
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ActiveInteractorTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var presenter: ActiveInteractor.ActivePresenter
  @Mock
  internal lateinit var listener: ActiveInteractor.Listener
  @Mock
  internal lateinit var router: ActiveRouter

  private lateinit var interactor: ActiveInteractor

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestActiveInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun `an example test`() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<ActiveInteractor.ActivePresenter, ActiveRouter>(interactor, presenter, router, null)
    InteractorHelper.detach(interactor)

    throw RuntimeException("Remove this test and add real tests.")
  }
}