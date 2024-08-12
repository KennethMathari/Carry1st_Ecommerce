package com.carry1st.product.data.network.di

import com.carry1st.product.data.network.service.ProductService
import com.carry1st.product.data.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

val productNetworkModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).retryOnConnectionFailure(true).build()
    }


    single<Retrofit> {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder().baseUrl(BASE_URL).client(get())
            .addConverterFactory(Json.asConverterFactory(contentType)).build()
    }

    single<ProductService> {
        val retrofit = get<Retrofit>()
        retrofit.create()
    }
}