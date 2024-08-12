package com.carry1st.data.product.repository

import com.carry1st.product.model.ProductDomain
import com.carry1st.product.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductListFromServer(): Flow<ApiResult<List<ProductDomain>>>

}