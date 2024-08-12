package com.carry1st.product.ui

import com.carry1st.product.data.network.model.ProductDTO
import com.carry1st.product.domain.model.ProductDomain
import com.hannesdorfmann.instantiator.instance

object TestData {

    val productDomain: ProductDomain = instance()
    val productDTO: ProductDTO = instance()
}