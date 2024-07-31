package com.carry1st.ecommerce

import android.app.Application
import com.carry1st.ecommerce.di.appModule
import com.carry1st.ecommerce.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Carry1st : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Carry1st)
            modules(appModule, networkModule)
        }
    }
}