package com.carry1st.ecommerce.di

import com.carry1st.ecommerce.data.repository.ProductRepository
import com.carry1st.ecommerce.data.repository.ProductRepositoryImpl
import com.carry1st.ecommerce.ui.viewmodel.ProductListViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single<CoroutineDispatcher> {
        Dispatchers.IO
    }

    single<ProductRepository> {
        ProductRepositoryImpl(
            productService = get(), ioDispatcher = get()
        )
    }

    viewModelOf(::ProductListViewModel)
}