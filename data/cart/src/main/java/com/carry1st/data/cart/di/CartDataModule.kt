package com.carry1st.data.cart.di

import com.carry1st.data.cart.repository.CartRepository
import com.carry1st.data.cart.repository.CartRepositoryImpl
import org.koin.dsl.module

val cartDataModule = module {
    single<CartRepository> {
        CartRepositoryImpl(
            cartDao = get(), ioDispatcher = get()
        )
    }
}