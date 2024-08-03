package com.carry1st.ecommerce.data.repository.cart

import com.carry1st.ecommerce.domain.model.CartDomain
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun getCartItems(): Flow<List<CartDomain>>

    suspend fun addCartItem(cartItem: CartDomain)

    suspend fun deleteCartItem(cartItem: CartDomain)
}