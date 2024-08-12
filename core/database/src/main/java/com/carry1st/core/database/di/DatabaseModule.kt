package com.carry1st.core.database.di

import androidx.room.Room
import com.carry1st.core.database.Carry1stDatabase
import com.carry1st.core.database.dao.CartDao
import org.koin.dsl.module

val databaseModule = module{

    single<Carry1stDatabase> {
        Room.databaseBuilder(
            get(), Carry1stDatabase::class.java, "carry1st_db"
        ).build()
    }

    single<CartDao> {
        val database = get<Carry1stDatabase>()
        database.cartDao()
    }
}