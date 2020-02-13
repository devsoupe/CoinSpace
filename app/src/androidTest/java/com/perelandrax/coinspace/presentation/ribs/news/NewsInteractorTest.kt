package com.perelandrax.coinspace.presentation.ribs.news

import com.uber.rib.core.InteractorHelper
import com.uber.rib.core.RibTestBasePlaceholder
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewsInteractorTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var presenter: NewsInteractor.IcoPresenter
  @Mock
  internal lateinit var listener: NewsInteractor.Listener
  @Mock
  internal lateinit var router: NewsRouter

  private lateinit var interactor: NewsInteractor

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
    InteractorHelper.attach<NewsInteractor.IcoPresenter, NewsRouter>(interactor, presenter, router, null)
    InteractorHelper.detach(interactor)

    throw RuntimeException("Remove this test and add real tests.")
  }
}