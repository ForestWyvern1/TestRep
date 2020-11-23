package com.example.testapi.data

import android.content.Context
import android.content.SharedPreferences
import android.view.Display

class DataManager {
    private var shared : SharedPreferences
    private var baseContext : Context

    val api = Api.createApi()
    val apisign = ApiSign.createApi()

    fun isFirstLaunch(): Boolean = shared.getBoolean("isFirstLaunch", true)
    fun endFirstLaunch(): Boolean = shared.edit().putBoolean("isFirstLaunch", false).commit()

    fun rightLogin(): String = shared.getString("RightLogin", "vova@gmail.com").toString()
    fun rightPass() = shared.getString("RightPass", "vovan").toString()

    fun isLogin() : Boolean = shared.getBoolean("isCheckBoxActive", false)
    fun login() : Boolean = shared.edit().putBoolean("isCheckBoxActive", true).commit()
    fun logout() : Boolean = shared.edit().putBoolean("isCheckBoxActive", false).commit()



    constructor(baseContext: Context){
        this.baseContext = baseContext
        shared = baseContext.getSharedPreferences("WS", Context.MODE_PRIVATE)
    }
}