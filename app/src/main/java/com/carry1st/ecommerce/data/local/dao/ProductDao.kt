package com.carry1st.ecommerce.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.carry1st.ecommerce.data.local.entity.ProductEntity

@Dao
interface ProductDao{
    @Query("SELECT * FROM products")
    fun getProducts(): List<ProductEntity>

    @Upsert
    fun saveProducts(vararg products: List<ProductEntity>)
}
