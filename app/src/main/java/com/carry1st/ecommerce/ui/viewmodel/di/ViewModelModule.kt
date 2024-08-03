package com.carry1st.ecommerce.ui.viewmodel.di

import com.carry1st.ecommerce.ui.viewmodel.CartViewModel
import com.carry1st.ecommerce.ui.viewmodel.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ProductListViewModel)
    viewModelOf(::CartViewModel)
}