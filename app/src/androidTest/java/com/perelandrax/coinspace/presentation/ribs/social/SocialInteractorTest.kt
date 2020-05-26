package com.perelandrax.coinspace.presentation.ribs.social

import com.uber.rib.core.InteractorHelper
import com.uber.rib.core.RibTestBasePlaceholder
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SocialInteractorTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var presenter: SocialInteractor.UpcomingPresenter

  @Mock
  internal lateinit var listener: SocialInteractor.Listener

  @Mock
  internal lateinit var router: SocialRouter

  private lateinit var interactor: SocialInteractor

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
    InteractorHelper.attach<SocialInteractor.UpcomingPresenter, SocialRouter>(interactor, presenter, router, null)
    InteractorHelper.detach(interactor)

    throw RuntimeException("Remove this test and add real tests.")
  }
}