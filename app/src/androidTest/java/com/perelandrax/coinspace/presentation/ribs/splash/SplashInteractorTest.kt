package com.perelandrax.coinspace.presentation.ribs.splash

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SplashInteractorTest : RibTestBasePlaceholder() {

    @Mock internal lateinit var presenter: SplashInteractor.SplashPresenter
    @Mock internal lateinit var listener: SplashInteractor.Listener
    @Mock internal lateinit var router: SplashRouter

    private lateinit var interactor: SplashInteractor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        interactor = TestSplashInteractor.create(presenter)
    }

    /**
     * TODO: Delete this example and add real tests.
     */
    @Test
    fun `an example test`() {
        // Use InteractorHelper to drive your interactor's lifecycle.
        InteractorHelper.attach<SplashInteractor.SplashPresenter, SplashRouter>(interactor, presenter, router, null)
        InteractorHelper.detach(interactor)

        throw RuntimeException("Remove this test and add real tests.")
    }
}