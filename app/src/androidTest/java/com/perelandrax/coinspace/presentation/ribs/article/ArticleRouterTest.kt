package com.perelandrax.coinspace.presentation.ribs.article

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ArticleRouterTest : RibTestBasePlaceholder() {

  @Mock
  internal lateinit var component: ArticleBuilder.Component
  @Mock
  internal lateinit var interactor: ArticleInteractor
  @Mock
  internal lateinit var view: ArticleView

  private lateinit var router: ArticleRouter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = ArticleRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun `an example test`() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router)
    RouterHelper.detach(router)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

