package com.carry1st.ecommerce.data.repository.cart

import com.carry1st.ecommerce.data.local.dao.CartDao
import com.carry1st.ecommerce.data.mapper.toCartDomain
import com.carry1st.ecommerce.data.mapper.toCartEntity
import com.carry1st.ecommerce.domain.model.CartDomain
import com.carry1st.ecommerce.domain.repository.CartRepository
import com.carry1st.ecommerce.domain.utils.LocalDBResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CartRepositoryImpl(
    private val cartDao: CartDao, private val ioDispatcher: CoroutineDispatcher
) : CartRepository {
    override suspend fun getCartItems(): Flow<LocalDBResult<List<CartDomain>>> {
        return flow {
            runCatching {
                cartDao.getCartList().map { cartEntityList ->
                    cartEntityList.map { cartEntity ->
                        cartEntity.toCartDomain()
                    }
                }
            }.onSuccess { listFlow ->
                listFlow.collect { cartDomainList ->
                    emit(LocalDBResult.Success(cartDomainList))
                }
            }.onFailure { throwable ->
                throwable.printStackTrace()
                emit(LocalDBResult.Error(throwable))
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