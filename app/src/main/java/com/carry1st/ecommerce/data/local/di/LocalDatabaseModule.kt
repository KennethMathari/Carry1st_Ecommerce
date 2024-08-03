package com.carry1st.ecommerce.data.local.di

import androidx.room.Room
import com.carry1st.ecommerce.data.local.Carry1stDatabase
import com.carry1st.ecommerce.data.local.dao.CartDao
import com.carry1st.ecommerce.data.local.dao.ProductDao
import org.koin.dsl.module

val localDatabaseModule = module {

    single<Carry1stDatabase> {
        Room.databaseBuilder(
            get(), Carry1stDatabase::class.java, "carry1st_db"
        ).build()
    }

    single<ProductDao> {
        val database = get<Carry1stDatabase>()
        database.productDao()
    }

    single<CartDao> {
        val database = get<Carry1stDatabase>()
        database.cartDao()
    }
}