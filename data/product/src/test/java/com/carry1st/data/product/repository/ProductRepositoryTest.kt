package com.carry1st.data.product.repository

import com.carry1st.data.product.TestData.productDomain
import com.carry1st.data.product.TestData.throwable
import com.carry1st.product.utils.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductRepositoryTest {

    private val productRepository: ProductRepository = mockk()

    @Test
    fun getProductListFromServerReturnsSuccessResult() = runTest {
        coEvery {
            productRepository.getProductListFromServer()
        } returns flowOf(ApiResult.Success(listOf(productDomain)))

        val result = productRepository.getProductListFromServer().first()

        assertEquals(ApiResult.Success(listOf(productDomain)), result)
    }

    @Test
    fun getProductListFromServerReturnsClientError() = runTest {
        coEvery {
            productRepository.getProductListFromServer()
        } returns flowOf(ApiResult.ClientError(throwable))

        val result = productRepository.getProductListFromServer().first()

        assertEquals(ApiResult.ClientError(throwable), result)
    }

    @Test
    fun getProductListFromServerReturnsNetworkError() = runTest {
        coEvery {
            productRepository.getProductListFromServer()
        } returns flowOf(ApiResult.NetworkError(throwable))

        val result = productRepository.getProductListFromServer().first()

        assertEquals(ApiResult.NetworkError(throwable), result)
    }

    @Test
    fun getProductListFromServerReturnsServerError() = runTest {
        coEvery {
            productRepository.getProductListFromServer()
        } returns flowOf(ApiResult.ServerError(throwable))

        val result = productRepository.getProductListFromServer().first()

        assertEquals(ApiResult.ServerError(throwable), result)
    }
}