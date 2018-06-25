package com.a2a.android.hbtf.data.network

import com.a2a.android.hbtf.data.ApiService
import com.weatherapp.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient


class NetworkModule {
    companion object {
        lateinit var retrofit: Retrofit
        lateinit var apiService: ApiService

    }

    /**
     * Create an instance of Retrofit object
     */
    fun getRetrofitInstance(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY


        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)  // <-- this is the important line!


        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build()

        provideAPIService()

        return retrofit
    }

    fun provideAPIService() {
        apiService = retrofit.create(ApiService::class.java)
    }


}