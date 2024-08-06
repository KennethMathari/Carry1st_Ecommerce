package com.carry1st.ecommerce

import android.app.Application
import com.carry1st.cart.ui.viewmodel.di.cartViewModelModule
import com.carry1st.core.data.local.di.localCartDBModule
import com.carry1st.core.data.repository.di.cartRepositoryModule
import com.carry1st.product.data.local.di.localProductDBModule
import com.carry1st.product.data.network.di.productNetworkModule
import com.carry1st.product.data.repository.di.productRepositoryModule
import com.carry1st.product.ui.viewmodel.di.productViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Carry1st : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Carry1st)
            modules(
                localProductDBModule,
                productNetworkModule,
                productRepositoryModule,
                productViewModelModule,

                localCartDBModule,
                cartRepositoryModule,
                cartViewModelModule
            )
        }
    }
}