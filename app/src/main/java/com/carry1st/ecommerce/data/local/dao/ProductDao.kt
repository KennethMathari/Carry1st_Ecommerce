package com.carry1st.ecommerce.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.carry1st.ecommerce.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao{
    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductEntity>>

    @Upsert
    suspend fun saveProducts(products: List<ProductEntity>)
}
