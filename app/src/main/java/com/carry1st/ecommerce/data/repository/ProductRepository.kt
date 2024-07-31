package com.carry1st.ecommerce.data.repository

import com.carry1st.ecommerce.domain.utils.NetworkResult
import com.carry1st.ecommerce.domain.model.ProductDomain
import kotlinx.coroutines.flow.Flow


interface ProductRepository {

    suspend fun getProductList(): Flow<NetworkResult<List<ProductDomain>>>
}