package com.carry1st.product.data.mapper

import com.carry1st.product.data.TestData.productDTO
import org.junit.Assert.assertEquals
import org.junit.Test

class ToDomainTest {

    @Test
    fun toProductDomainReturnsProductDomain() {

        val productDomain = productDTO.toProductDomain()

        assertEquals(productDTO.id, productDomain.id)
    }
}