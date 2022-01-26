package com.example.cleanandroidarchitecture.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.cleanandroidarchitecture.R
import com.example.cleanandroidarchitecture.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
//    @Inject lateinit var vmFactory: SplashViewModelFactory

//    private val vm: SplashViewModel by lazy {
//        ViewModelProvider(this, vmFactory)[SplashViewModel::class.java]
//    }

    private val vm: SplashViewModel by viewModels()

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