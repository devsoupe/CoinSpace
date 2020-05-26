package com.perelandrax.coinspace.presentation.ribs.navigation

import com.uber.rib.core.InteractorHelper
import com.uber.rib.core.RibTestBasePlaceholder
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NavigationInteractorTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var presenter: NavigationInteractor.MainBottomTabPresenter

  @Mock
  internal lateinit var listener: NavigationInteractor.Listener

  @Mock
  internal lateinit var router: NavigationRouter

  private lateinit var interactor: NavigationInteractor

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestMainBottomTabInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun `an example test`() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<NavigationInteractor.MainBottomTabPresenter, NavigationRouter>(
      interactor,
      presenter,
      router,
      null
    )
    InteractorHelper.detach(interactor)

    throw RuntimeException("Remove this test and add real tests.")
  }
}