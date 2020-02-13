package com.perelandrax.coinspace.presentation.ribs.article

import com.uber.rib.core.InteractorHelper
import com.uber.rib.core.RibTestBasePlaceholder
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ArticleInteractorTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var presenter: ArticleInteractor.ActivePresenter
  @Mock
  internal lateinit var listener: ArticleInteractor.Listener
  @Mock
  internal lateinit var router: ArticleRouter

  private lateinit var interactor: ArticleInteractor

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
    InteractorHelper.attach<ArticleInteractor.ActivePresenter, ArticleRouter>(interactor, presenter, router, null)
    InteractorHelper.detach(interactor)

    throw RuntimeException("Remove this test and add real tests.")
  }
}