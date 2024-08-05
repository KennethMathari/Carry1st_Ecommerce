package com.carry1st.product.data.local.di

import com.carry1st.product.data.local.SearchManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val localProductDBModule = module {
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