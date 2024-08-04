package com.carry1st.ecommerce.domain.repository

import com.carry1st.ecommerce.domain.model.ProductDomain
import com.carry1st.ecommerce.domain.utils.ApiResult
import kotlinx.coroutines.flow.Flow


interface ProductRepository {

    suspend fun getProductListFromServer(): Flow<ApiResult<List<ProductDomain>>>

}