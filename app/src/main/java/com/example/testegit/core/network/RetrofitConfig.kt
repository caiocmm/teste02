package com.example.testegit.core.network

import com.example.testegit.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitConfig {

    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClientBuilder = OkHttpClient.Builder()

        httpClientBuilder.connectTimeout(65, TimeUnit.SECONDS)
            .readTimeout(65, TimeUnit.SECONDS)
            .writeTimeout(65, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(interceptor)

        return httpClientBuilder.build()

    }

}

inline fun <reified T> createWebService(): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(RetrofitConfig().providesOkHttpClient())
        .build()
        .create(T::class.java)
}