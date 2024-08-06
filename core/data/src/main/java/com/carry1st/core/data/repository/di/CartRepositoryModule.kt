package com.carry1st.core.data.repository.di

import com.carry1st.core.data.repository.CartRepository
import com.carry1st.core.data.repository.CartRepositoryImpl
import org.koin.dsl.module

val cartRepositoryModule = module {
    single<CartRepository> {
        CartRepositoryImpl(
            cartDao = get(), ioDispatcher = get()
        )
    }
}