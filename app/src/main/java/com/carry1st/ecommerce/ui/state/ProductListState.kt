package com.carry1st.ecommerce.ui.state

import com.carry1st.ecommerce.ui.model.ProductPresentation

data class ProductListState(
    val productList: List<ProductPresentation>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = ""
)
