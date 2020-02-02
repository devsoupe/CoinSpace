package com.perelandrax.coincraft.ribs.coins

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CoinsInteractorTest : RibTestBasePlaceholder() {

    @Mock internal lateinit var presenter: CoinsInteractor.CoinsPresenter
    @Mock internal lateinit var listener: CoinsInteractor.Listener
    @Mock internal lateinit var router: CoinsRouter

    private lateinit var interactor: CoinsInteractor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        interactor = TestCoinsInteractor.create(presenter)
    }

    /**
     * TODO: Delete this example and add real tests.
     */
    @Test
    fun `an example test`() {
        // Use InteractorHelper to drive your interactor's lifecycle.
        InteractorHelper.attach<CoinsInteractor.CoinsPresenter, CoinsRouter>(interactor, presenter, router, null)
        InteractorHelper.detach(interactor)

        throw RuntimeException("Remove this test and add real tests.")
    }
}