package com.carry1st.product.data

import com.carry1st.product.data.local.document.ProductDocument
import com.carry1st.product.data.network.model.ProductDTO
import com.carry1st.product.domain.model.ProductDomain
import com.hannesdorfmann.instantiator.instance
import io.mockk.mockk

object TestData {

    val productDomain: ProductDomain = instance()

    val randomString: String = instance()

    val productDTO: ProductDTO = instance()

    val productDocument: ProductDocument = instance()

    val throwable: Throwable = mockk()
}