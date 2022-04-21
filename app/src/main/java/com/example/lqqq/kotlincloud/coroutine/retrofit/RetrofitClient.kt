package com.example.lqqq.kotlincloud.coroutine.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitClient {
    private val instance: Retrofit by lazy{
        Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("")
            .addConverterFactory(null)
            .build()
    }
    val api:Api by lazy{
        instance.create(Api::class.java)
    }
}