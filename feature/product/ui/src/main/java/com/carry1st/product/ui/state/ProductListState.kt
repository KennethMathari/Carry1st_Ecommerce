package com.carry1st.product.ui.state

import com.carry1st.product.ui.model.ProductPresentation

data class ProductListState(
    val productList: List<ProductPresentation>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = ""
)
