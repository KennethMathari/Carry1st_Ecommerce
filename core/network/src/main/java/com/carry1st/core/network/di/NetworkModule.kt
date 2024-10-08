package com.carry1st.core.network.di

import com.carry1st.core.network.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(true).build()
    }


    single<Retrofit> {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder().baseUrl(BASE_URL).client(get())
            .addConverterFactory(Json.asConverterFactory(contentType)).build()
    }
}