package com.carry1st.data.product.di

import com.carry1st.data.product.local.SearchManager
import com.carry1st.data.product.network.service.ProductService
import com.carry1st.data.product.repository.ProductRepository
import com.carry1st.data.product.repository.ProductRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val productDataModule = module {
    single<CoroutineScope> {
        CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    single<SearchManager> {
        SearchManager(
            context = get(), ioDispatcher = get(), scope = get()
        )
    }

    single<ProductService> {
        val retrofit = get<Retrofit>()
        retrofit.create()
    }

    single<CoroutineDispatcher> {
        Dispatchers.IO
    }

    single<ProductRepository> {
        ProductRepositoryImpl(
            productService = get(), ioDispatcher = get()
        )
    }
}