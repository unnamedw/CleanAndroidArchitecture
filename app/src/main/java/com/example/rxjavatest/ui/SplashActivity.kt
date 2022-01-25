package com.example.rxjavatest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.rxjavatest.R
import com.example.rxjavatest.ui.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    val vm: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        vm.init()
        vm.moveToMainEvent.observe(this, {
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME)
            startActivity(mainIntent)
        })
    }
}