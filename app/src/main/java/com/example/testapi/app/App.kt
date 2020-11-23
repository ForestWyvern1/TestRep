package com.example.testapi.app

import android.app.Application
import com.example.testapi.data.DataManager

class App : Application() {
    companion object {
        lateinit var dm: DataManager
    }

    override fun onCreate() {
        super.onCreate()
        dm = DataManager(baseContext)
    }
}