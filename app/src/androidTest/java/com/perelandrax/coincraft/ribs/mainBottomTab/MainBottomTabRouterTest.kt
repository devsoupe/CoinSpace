package com.perelandrax.coincraft.ribs.mainBottomTab

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainBottomTabRouterTest : RibTestBasePlaceholder() {

    @Mock internal lateinit var component: MainBottomTabBuilder.Component
    @Mock internal lateinit var interactor: MainBottomTabInteractor
    @Mock internal lateinit var view: MainBottomTabView

    private lateinit var router: MainBottomTabRouter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        router = MainBottomTabRouter(view, interactor, component)
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

