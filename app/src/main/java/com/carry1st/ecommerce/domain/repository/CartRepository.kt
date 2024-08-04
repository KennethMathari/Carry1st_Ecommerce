package com.carry1st.ecommerce.domain.repository

import com.carry1st.ecommerce.domain.model.CartDomain
import com.carry1st.ecommerce.domain.utils.LocalDBResult
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun getCartItems(): Flow<LocalDBResult<List<CartDomain>>>

    suspend fun addCartItem(cartItem: CartDomain)

    suspend fun deleteCartItem(cartItem: CartDomain)
}