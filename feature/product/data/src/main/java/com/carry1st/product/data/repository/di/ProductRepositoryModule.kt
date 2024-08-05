package com.carry1st.product.data.repository.di

import com.carry1st.product.data.repository.ProductRepository
import com.carry1st.product.data.repository.ProductRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val productRepositoryModule = module {

    single<CoroutineDispatcher> {
        Dispatchers.IO
    }

    single<ProductRepository> {
        ProductRepositoryImpl(
            productService = get(), ioDispatcher = get()
        )
    }

}