package com.carry1st.ecommerce.data.network.di

import com.carry1st.ecommerce.data.network.service.ProductService
import com.carry1st.ecommerce.domain.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val networkModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).retryOnConnectionFailure(true).build()
    }

    single<Moshi> {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    single<Retrofit> {
        Retrofit.Builder().baseUrl(BASE_URL).client(get())
            .addConverterFactory(MoshiConverterFactory.create(get())).build()
    }

    single<ProductService> {
        val retrofit = get<Retrofit>()
        retrofit.create()
    }
}