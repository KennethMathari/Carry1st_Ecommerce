package com.carry1st.ecommerce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carry1st.ecommerce.data.local.dao.ProductDao
import com.carry1st.ecommerce.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class Carry1stDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}