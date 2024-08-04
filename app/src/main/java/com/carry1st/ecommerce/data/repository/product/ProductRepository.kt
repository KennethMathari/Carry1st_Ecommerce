package com.carry1st.ecommerce.data.repository.product

import com.carry1st.ecommerce.domain.model.ProductDomain
import com.carry1st.ecommerce.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow


interface ProductRepository {

    suspend fun getProductListFromServer(): Flow<NetworkResult<List<ProductDomain>>>

}