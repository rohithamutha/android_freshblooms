package com.example.freshblooms.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://mswlgdzd-80.inc1.devtunnels.ms/freshblooms/api/"
    const val IMAGE_URL = "https://mswlgdzd-80.inc1.devtunnels.ms/freshblooms/images/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.MINUTES)   // Connection timeout
        .readTimeout(30, TimeUnit.MINUTES)      // Read timeout
        .writeTimeout(30, TimeUnit.MINUTES)     // Write timeout
        .build()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)  // Attach custom client
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
