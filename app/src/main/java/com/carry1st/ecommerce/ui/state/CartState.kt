package com.carry1st.ecommerce.ui.state

import com.carry1st.ecommerce.ui.model.CartPresentation

data class CartState(
    val cartList: List<CartPresentation>? = emptyList(),
    val errorMessage: String? = null
)
