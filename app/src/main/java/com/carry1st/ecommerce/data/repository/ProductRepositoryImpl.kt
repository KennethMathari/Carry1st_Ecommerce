package com.carry1st.ecommerce.data.repository

import com.carry1st.ecommerce.data.mapper.toProductDomain
import com.carry1st.ecommerce.data.network.service.ProductService
import com.carry1st.ecommerce.data.network.utils.safeApiCall
import com.carry1st.ecommerce.domain.utils.NetworkResult
import com.carry1st.ecommerce.domain.model.ProductDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductRepositoryImpl(
    private val productService: ProductService, private val ioDispatcher: CoroutineDispatcher
) : ProductRepository {
    override suspend fun getProductList(): Flow<NetworkResult<List<ProductDomain>>> {
        return flow {
            val result = safeApiCall {
                productService.getProductList().map { productDTO ->
                    productDTO.toProductDomain()
                }
            }
            emit(result)
        }.flowOn(ioDispatcher)
    }

}