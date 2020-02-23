package com.perelandrax.coinspace.presentation.ribs.coinwebsite

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CoinWebsiteInteractorTest : RibTestBasePlaceholder() {

    @Mock internal lateinit var presenter: CoinWebsiteInteractor.CoinWebsitePresenter
    @Mock internal lateinit var listener: CoinWebsiteInteractor.Listener
    @Mock internal lateinit var router: CoinWebsiteRouter

    private lateinit var interactor: CoinWebsiteInteractor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        interactor = TestCoinWebsiteInteractor.create(presenter)
    }

    /**
     * TODO: Delete this example and add real tests.
     */
    @Test
    fun `an example test`() {
        // Use InteractorHelper to drive your interactor's lifecycle.
        InteractorHelper.attach<CoinWebsiteInteractor.CoinWebsitePresenter, CoinWebsiteRouter>(interactor, presenter, router, null)
        InteractorHelper.detach(interactor)

        throw RuntimeException("Remove this test and add real tests.")
    }
}