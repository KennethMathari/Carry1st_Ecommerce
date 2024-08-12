package com.carry1st.product.ui.viewmodel

import app.cash.turbine.test
import com.carry1st.data.product.local.SearchManager
import com.carry1st.data.product.repository.ProductRepository
import com.carry1st.product.ui.TestData.productDomain
import com.carry1st.product.utils.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductListViewModelTest {
    private lateinit var productListViewModel: ProductListViewModel
    private val productRepository: ProductRepository = mockk()
    private val searchManager: SearchManager = mockk()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {

        coEvery {
            productRepository.getProductListFromServer()
        } returns flowOf(ApiResult.Success(listOf(productDomain)))

        coEvery {
            searchManager.addProductList(listOf(productDomain))
        } returns true

        coEvery {
            searchManager.fetchProductListFromLocalStorage()
        } returns listOf(productDomain)

        productListViewModel = ProductListViewModel(
            productRepository = productRepository, searchManager = searchManager
        )
    }


    @Test
    fun `Loading state is updated correctly when getProductListFromServer() is triggered`() =
        runTest {
            productListViewModel.productListState.test {
                assertFalse(awaitItem().isLoading)
                productListViewModel.getProductListFromServer()

                assertTrue(awaitItem().isLoading)

                assertFalse(awaitItem().isLoading)
                cancelAndIgnoreRemainingEvents()
            }
        }
}