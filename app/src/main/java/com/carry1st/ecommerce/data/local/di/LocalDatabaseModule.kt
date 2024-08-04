package com.carry1st.ecommerce.data.local.di

import androidx.room.Room
import com.carry1st.ecommerce.data.local.Carry1stDatabase
import com.carry1st.ecommerce.data.local.SearchManager
import com.carry1st.ecommerce.data.local.dao.CartDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val localDatabaseModule = module {

    single<Carry1stDatabase> {
        Room.databaseBuilder(
            get(), Carry1stDatabase::class.java, "carry1st_db"
        ).build()
    }

    single<CartDao> {
        val database = get<Carry1stDatabase>()
        database.cartDao()
    }

    single<CoroutineScope> {
        CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    single<SearchManager> {
        SearchManager(
            context = get(),
            ioDispatcher = get(),
            scope = get()
        )
    }
}