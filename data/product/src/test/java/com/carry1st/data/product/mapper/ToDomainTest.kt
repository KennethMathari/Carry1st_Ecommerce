package com.carry1st.data.product.mapper

import com.carry1st.data.product.TestData.productDTO
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ToDomainTest {

    @Test
    fun toProductDomainReturnsProductDomain() {

        val productDomain = productDTO.toProductDomain()

        assertEquals(productDTO.id, productDomain.id)
    }
}