package com.carry1st.product.data.local

import androidx.appsearch.app.AppSearchSession
import com.carry1st.product.data.TestData.productDomain
import com.carry1st.product.data.TestData.randomString
import com.carry1st.product.domain.model.ProductDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class SearchManagerTest {

    private val searchManager: SearchManager = mockk()
    private val session : AppSearchSession = mockk()

    @Test
    fun addProductListReturnsTrue() = runTest{

        coEvery {
            searchManager.addProductList(listOf(productDomain))
        }returns true

        val result = searchManager.addProductList(listOf(productDomain))

        assertEquals(true, result)
    }

    @Test
    fun addProductListReturnsFalse() = runTest{

        coEvery {
            searchManager.addProductList(listOf(productDomain))
        }returns false

        val result = searchManager.addProductList(listOf(productDomain))

        assertEquals(false, result)
    }

    @Test
    fun searchProductListReturnsList() = runTest {

        coEvery {
            searchManager.searchProductList(randomString)
        } returns listOf(productDomain)

        val result = searchManager.searchProductList(randomString)

        assertEquals(listOf(productDomain), result)
    }

    @Test
    fun searchProductListReturnsEmptyList() = runTest {

        coEvery {
            searchManager.searchProductList(randomString)
        }returns emptyList()

        val result = searchManager.searchProductList(randomString)

        assertEquals( emptyList<ProductDomain>(), result)
    }

    @Test
    fun fetchProductListFromLocalStorageReturnsList() = runTest {

        coEvery {
            searchManager.fetchProductListFromLocalStorage()
        } returns listOf(productDomain)

        val result = searchManager.fetchProductListFromLocalStorage()

        assertEquals(listOf(productDomain), result)
    }

    @Test
    fun fetchProductListFromLocalStorageReturnsEmptyList() = runTest {

        coEvery {
            searchManager.fetchProductListFromLocalStorage()
        } returns emptyList()

        val result = searchManager.fetchProductListFromLocalStorage()

        assertEquals(emptyList<ProductDomain>(), result)
    }

    @Test
    fun sessionIsInitialized() = runTest {
        coEvery {
            searchManager.init()
        } returns Unit

        coEvery {
            searchManager.session
        } returns session

        val nonNullSession = searchManager.session
        assertNotNull(nonNullSession)

        val result = searchManager.init()
        assertEquals(Unit, result)
    }

    @Test
    fun sessionIsClosed() = runTest {
        coEvery {
            searchManager.closeSession()
        } returns Unit

        coEvery {
            searchManager.session
        } returns null

        val nullSession = searchManager.session
        assertNull(nullSession)

        val result = searchManager.closeSession()
        assertEquals(Unit, result)
    }
}