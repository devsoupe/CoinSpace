package com.perelandrax.coinspace.presentation.ribs.coinwebsite

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CoinWebsiteRouterTest : RibTestBasePlaceholder() {

    @Mock internal lateinit var component: CoinWebsiteBuilder.Component
    @Mock internal lateinit var interactor: CoinWebsiteInteractor
    @Mock internal lateinit var view: CoinWebsiteView

    private lateinit var router: CoinWebsiteRouter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        router = CoinWebsiteRouter(view, interactor, component)
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

