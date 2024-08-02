package com.carry1st.ecommerce.data.repository

import android.util.Log
import com.carry1st.ecommerce.data.local.dao.ProductDao
import com.carry1st.ecommerce.data.mapper.toProductDomain
import com.carry1st.ecommerce.data.mapper.toProductEntity
import com.carry1st.ecommerce.data.network.service.ProductService
import com.carry1st.ecommerce.data.network.utils.safeApiCall
import com.carry1st.ecommerce.domain.model.ProductDomain
import com.carry1st.ecommerce.domain.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val productService: ProductService,
    private val ioDispatcher: CoroutineDispatcher,
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun getProductListFromServer(): Flow<NetworkResult<List<ProductDomain>>> {
        return flow {
            val result = safeApiCall {
                productService.getProductList().map { productDTO ->
                    productDTO.toProductDomain()
                }
            }
            emit(result)
        }.flowOn(ioDispatcher)
    }

    override suspend fun saveProductsToLocalDB(products: List<ProductDomain>) {
        return withContext(ioDispatcher) {
            try {
                val productListEntity = products.map { productDomain ->
                    productDomain.toProductEntity()

                }
                productDao.saveProducts(productListEntity)
            } catch (e: Exception) {
                Log.e("SAVEProductsLocalDB",e.toString())
                e.printStackTrace()
            }
        }
    }

    override suspend fun getProductListFromLocalDB(): Flow<List<ProductDomain>> {
        return flow {
            try {
                productDao.getProducts().map { productEntityList->
                    productEntityList.map { productEntity->
                        productEntity.toProductDomain()
                    }
                }.collect{ productDomainList->
                    emit(productDomainList)
                }

            } catch (e: Exception){
                Log.e("GETProductsLocalDB",e.toString())
                e.printStackTrace()
            }
        }.flowOn(ioDispatcher)
    }

}