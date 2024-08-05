package com.carry1st.cart.data.local.di

import androidx.room.Room
import com.carry1st.cart.data.local.Carry1stDatabase
import com.carry1st.cart.data.local.dao.CartDao
import org.koin.dsl.module

val localCartDBModule = module{

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