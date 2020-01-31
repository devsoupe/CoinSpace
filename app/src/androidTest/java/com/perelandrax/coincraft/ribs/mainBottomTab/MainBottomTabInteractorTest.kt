package com.perelandrax.coincraft.ribs.mainBottomTab

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainBottomTabInteractorTest : RibTestBasePlaceholder() {

    @Mock internal lateinit var presenter: MainBottomTabInteractor.MainBottomTabPresenter
    @Mock internal lateinit var listener: MainBottomTabInteractor.Listener
    @Mock internal lateinit var router: MainBottomTabRouter

    private lateinit var interactor: MainBottomTabInteractor

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
        InteractorHelper.attach<MainBottomTabInteractor.MainBottomTabPresenter, MainBottomTabRouter>(interactor, presenter, router, null)
        InteractorHelper.detach(interactor)

        throw RuntimeException("Remove this test and add real tests.")
    }
}