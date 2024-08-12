package com.carry1st.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carry1st.core.database.dao.CartDao
import com.carry1st.core.database.entity.CartEntity

@Database(
    entities = [CartEntity::class], version = 1
)
abstract class Carry1stDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}