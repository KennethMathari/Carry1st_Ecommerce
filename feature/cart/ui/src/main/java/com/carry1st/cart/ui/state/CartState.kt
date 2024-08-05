package com.carry1st.cart.ui.state

import com.carry1st.cart.ui.model.CartPresentation

data class CartState(
    val cartList: List<CartPresentation>? = emptyList(),
    val errorMessage: String? = null
)