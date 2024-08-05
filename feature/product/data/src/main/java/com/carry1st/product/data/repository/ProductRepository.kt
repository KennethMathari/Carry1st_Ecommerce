package com.carry1st.product.data.repository

import com.carry1st.product.domain.model.ProductDomain
import com.carry1st.product.domain.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductListFromServer(): Flow<ApiResult<List<ProductDomain>>>

}