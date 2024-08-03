package com.carry1st.ecommerce

import android.app.Application
import com.carry1st.ecommerce.data.local.di.localDatabaseModule
import com.carry1st.ecommerce.data.network.di.networkModule
import com.carry1st.ecommerce.data.repository.di.repositoryModule
import com.carry1st.ecommerce.ui.viewmodel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Carry1st : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Carry1st)
            modules(
                viewModelModule, networkModule, localDatabaseModule, repositoryModule
            )
        }
    }
}