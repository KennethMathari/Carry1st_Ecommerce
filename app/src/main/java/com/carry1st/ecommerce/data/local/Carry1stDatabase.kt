package com.carry1st.ecommerce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carry1st.ecommerce.data.local.dao.CartDao
import com.carry1st.ecommerce.data.local.dao.ProductDao
import com.carry1st.ecommerce.data.local.entity.CartEntity
import com.carry1st.ecommerce.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, CartEntity::class], version = 1
)
abstract class Carry1stDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
}