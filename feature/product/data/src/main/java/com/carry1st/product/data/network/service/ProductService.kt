package com.carry1st.product.data.network.service

import com.carry1st.product.data.network.model.ProductDTO
import retrofit2.http.GET

interface ProductService {
    @GET("/carry1stdeveloper/mock-product-api/productBundles")
    suspend fun getProductList(): List<ProductDTO>
}