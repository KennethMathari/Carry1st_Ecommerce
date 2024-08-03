package com.carry1st.ecommerce.data.repository.cart

import com.carry1st.ecommerce.data.local.dao.CartDao
import com.carry1st.ecommerce.data.mapper.toCartDomain
import com.carry1st.ecommerce.data.mapper.toCartEntity
import com.carry1st.ecommerce.domain.model.CartDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CartRepositoryImpl(
    private val cartDao: CartDao, private val ioDispatcher: CoroutineDispatcher
) : CartRepository {
    override suspend fun getCartItems(): Flow<List<CartDomain>> {
        return flow {
            try {
                cartDao.getCartList().map { cartEntityList ->
                    cartEntityList.map { cartEntity ->
                        cartEntity.toCartDomain()
                    }
                }.collect { cartDomainList ->
                    emit(cartDomainList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun addCartItem(cartItem: CartDomain) {
        return withContext(ioDispatcher) {
            try {
                val cartItemEntity = cartItem.toCartEntity()
                cartDao.addCartItem(cartItemEntity)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun deleteCartItem(cartItem: CartDomain) {
        return withContext(ioDispatcher) {
            try {
                val cartItemEntity = cartItem.toCartEntity()
                cartDao.deleteCartItem(cartItemEntity)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}