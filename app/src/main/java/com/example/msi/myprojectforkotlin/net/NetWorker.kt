package com.example.msi.myprojectforkotlin.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by MSI on 2018/5/22.
 */
object NetWorker {
    private val retrofit: Retrofit
    private const val BASE_URL = "http://baobab.kaiyanapp.com/api/"
    private const val DEFAULT_TIMEOUT = 5L
    private val okHttpClient: OkHttpClient

    init {
        val longing = Interceptor { chain ->
            val request = chain.request()
            chain.proceed(request)
        }

        okHttpClient = OkHttpClient.Builder().connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(longing)
                .build()

        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build()
    }

    val apiService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}