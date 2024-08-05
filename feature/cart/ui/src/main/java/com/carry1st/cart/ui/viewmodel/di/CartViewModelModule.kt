package com.carry1st.cart.ui.viewmodel.di

import com.carry1st.cart.ui.viewmodel.CartViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val cartViewModelModule = module {
    viewModelOf(::CartViewModel)
}