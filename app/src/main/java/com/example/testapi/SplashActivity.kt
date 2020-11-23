package com.example.testapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapi.app.App

class SplashActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        if (App.dm.isFirstLaunch()){
            startActivity(Intent(this, FirstLaunchActivity::class.java))
        }
        else if (App.dm.isLogin()){
            startActivity(Intent(this, MainActivity::class.java))
        }
        else startActivity(Intent(this,SignActivity::class.java))
    }
}