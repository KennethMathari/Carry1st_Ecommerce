package com.carry1st.ecommerce.ui.state

import com.carry1st.ecommerce.domain.model.ProductDomain

data class ProductListState(
    val productList: List<ProductDomain>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
