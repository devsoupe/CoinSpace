package com.perelandrax.coincraft.ribs

import com.uber.rib.core.InteractorHelper
import com.uber.rib.core.RibTestBasePlaceholder
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class IcoInteractorTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var presenter: IcoInteractor.IcoPresenter
  @Mock
  internal lateinit var listener: IcoInteractor.Listener
  @Mock
  internal lateinit var router: IcoRouter

  private lateinit var interactor: IcoInteractor

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestIcoInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun `an example test`() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<IcoInteractor.IcoPresenter, IcoRouter>(interactor, presenter, router, null)
    InteractorHelper.detach(interactor)

    throw RuntimeException("Remove this test and add real tests.")
  }
}