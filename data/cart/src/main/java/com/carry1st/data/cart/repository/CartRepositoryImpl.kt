package com.carry1st.data.cart.repository

import com.carry1st.core.database.dao.CartDao
import com.carry1st.data.cart.mapper.toCartDomain
import com.carry1st.data.cart.mapper.toCartEntity
import com.carry1st.domain.cart.model.CartDomain
import com.carry1st.domain.cart.utils.DatabaseResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CartRepositoryImpl(
    private val cartDao: CartDao,
    private val ioDispatcher: CoroutineDispatcher
) : CartRepository {
    override suspend fun getCartItems(): Flow<DatabaseResult<List<CartDomain>>> {
        return flow {
            runCatching {
                cartDao.getCartList().map { cartEntityList ->
                    cartEntityList.map { cartEntity ->
                        cartEntity.toCartDomain()
                    }
                }
            }.onSuccess { listFlow ->
                listFlow.collect { cartDomainList ->
                    emit(DatabaseResult.Success(cartDomainList))
                }
            }.onFailure { throwable ->
                throwable.printStackTrace()
                emit(DatabaseResult.Error(throwable))
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun addCartItem(cartItem: CartDomain) {
        return withContext(ioDispatcher) {
            runCatching {
                val cartItemEntity = cartItem.toCartEntity()
                cartDao.addCartItem(cartItemEntity)
            }.onFailure { throwable ->
                throwable.printStackTrace()
            }
        }
    }

    override suspend fun deleteCartItem(cartItem: CartDomain) {
        return withContext(ioDispatcher) {
            runCatching {
                val cartItemEntity = cartItem.toCartEntity()
                cartDao.deleteCartItem(cartItemEntity)
            }.onFailure { throwable ->
                throwable.printStackTrace()
            }
        }
    }
}