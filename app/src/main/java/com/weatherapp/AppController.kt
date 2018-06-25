package com.weatherapp

import android.app.Application
import com.a2a.android.hbtf.data.network.NetworkModule
import com.weatherapp.util.AppLogger


class AppController : Application() {

    companion object {
        lateinit var instance: AppController
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        AppLogger.init()
        NetworkModule().getRetrofitInstance()

    }
}