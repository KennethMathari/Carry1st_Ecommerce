package com.carry1st.data.cart.repository

import com.carry1st.domain.cart.model.CartDomain
import com.carry1st.domain.cart.utils.DatabaseResult
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun getCartItems(): Flow<DatabaseResult<List<CartDomain>>>

    suspend fun addCartItem(cartItem: CartDomain)

    suspend fun deleteCartItem(cartItem: CartDomain)
}