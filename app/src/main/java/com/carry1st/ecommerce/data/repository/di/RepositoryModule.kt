package com.carry1st.ecommerce.data.repository.di

import com.carry1st.ecommerce.data.repository.cart.CartRepository
import com.carry1st.ecommerce.data.repository.cart.CartRepositoryImpl
import com.carry1st.ecommerce.data.repository.product.ProductRepository
import com.carry1st.ecommerce.data.repository.product.ProductRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {

    single<CoroutineDispatcher> {
        Dispatchers.IO
    }

    single<CartRepository> {
        CartRepositoryImpl(
            cartDao = get(), ioDispatcher = get()
        )
    }

    single<ProductRepository> {
        ProductRepositoryImpl(
            productService = get(), ioDispatcher = get()
        )
    }

}