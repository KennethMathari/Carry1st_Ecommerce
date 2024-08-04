package com.carry1st.ecommerce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carry1st.ecommerce.data.local.dao.CartDao
import com.carry1st.ecommerce.data.local.entity.CartEntity

@Database(
    entities = [CartEntity::class], version = 1
)
abstract class Carry1stDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}