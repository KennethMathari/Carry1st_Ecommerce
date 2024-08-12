package com.carry1st.ecommerce

import android.app.Application
import com.carry1st.cart.ui.viewmodel.di.cartViewModelModule
import com.carry1st.core.database.di.databaseModule
import com.carry1st.core.network.di.networkModule
import com.carry1st.data.cart.di.cartDataModule
import com.carry1st.data.product.di.productDataModule
import com.carry1st.product.ui.viewmodel.di.productViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Carry1st : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Carry1st)
            modules(
                productViewModelModule,
                cartViewModelModule,
                databaseModule,
                networkModule,
                cartDataModule,
                productDataModule
            )
        }
    }
}