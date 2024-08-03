package com.carry1st.ecommerce.data.repository.product

import com.carry1st.ecommerce.domain.utils.NetworkResult
import com.carry1st.ecommerce.domain.model.ProductDomain
import kotlinx.coroutines.flow.Flow


interface ProductRepository {

    suspend fun getProductListFromServer(): Flow<NetworkResult<List<ProductDomain>>>

    suspend fun saveProductsToLocalDB(products: List<ProductDomain>)

    suspend fun getProductListFromLocalDB(): Flow<List<ProductDomain>>
}