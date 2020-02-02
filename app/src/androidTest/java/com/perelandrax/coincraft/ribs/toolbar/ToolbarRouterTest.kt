package com.perelandrax.coincraft.ribs.toolbar

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ToolbarRouterTest : RibTestBasePlaceholder() {

    @Mock internal lateinit var component: ToolbarBuilder.Component
    @Mock internal lateinit var interactor: ToolbarInteractor
    @Mock internal lateinit var view: ToolbarView

    private lateinit var router: ToolbarRouter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        router = ToolbarRouter(view, interactor, component)
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

