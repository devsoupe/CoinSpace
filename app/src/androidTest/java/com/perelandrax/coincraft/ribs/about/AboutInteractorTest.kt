package com.perelandrax.coincraft.ribs.about

import com.uber.rib.core.InteractorHelper
import com.uber.rib.core.RibTestBasePlaceholder
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AboutInteractorTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var presenter: AboutInteractor.AboutPresenter
  @Mock
  internal lateinit var listener: AboutInteractor.Listener
  @Mock
  internal lateinit var router: AboutRouter

  private lateinit var interactor: AboutInteractor

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestAboutInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun `an example test`() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<AboutInteractor.AboutPresenter, AboutRouter>(interactor, presenter, router, null)
    InteractorHelper.detach(interactor)

    throw RuntimeException("Remove this test and add real tests.")
  }
}