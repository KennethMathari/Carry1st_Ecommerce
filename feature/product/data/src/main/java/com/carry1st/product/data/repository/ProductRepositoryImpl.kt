package com.carry1st.product.data.repository

import com.carry1st.product.data.mapper.toProductDomain
import com.carry1st.product.data.network.service.ProductService
import com.carry1st.product.data.utils.safeApiCall
import com.carry1st.product.domain.model.ProductDomain
import com.carry1st.product.domain.utils.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductRepositoryImpl(
    private val productService: ProductService,
    private val ioDispatcher: CoroutineDispatcher
) : ProductRepository {

    override suspend fun getProductListFromServer(): Flow<ApiResult<List<ProductDomain>>> {
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