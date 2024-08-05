package com.carry1st.product.ui.viewmodel.di

import com.carry1st.product.ui.viewmodel.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val productViewModelModule = module {
    viewModelOf(::ProductListViewModel)
}