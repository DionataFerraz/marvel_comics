package com.dionataferraz.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun createNetworkClient(baseUrl: String = BuildConfig.HTTP_SERVER) =
    retrofitClient(
        baseUrl,
        httpClient(),
        gsonConverter()
    )

private fun gsonConverter() = GsonConverterFactory.create(GsonBuilder().create())

private fun httpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor())
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient, gsonConverter: GsonConverterFactory): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(gsonConverter)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
