package com.example.testapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapi.app.App
import kotlinx.android.synthetic.main.activity_first_launch.*

class FirstLaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_launch)
        App.dm.endFirstLaunch()
        welcome.setOnClickListener {
            startActivity(Intent(this,SignActivity::class.java))
        }
    }
}