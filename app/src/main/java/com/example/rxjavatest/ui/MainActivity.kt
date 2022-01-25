package com.example.rxjavatest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.rxjavatest.AppConstants
import com.example.rxjavatest.R
import com.example.rxjavatest.databinding.ActivityMainBinding
import com.example.rxjavatest.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
//        setContentView(R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.vm = this.vm
        vm.post.observe(this, {
            Log.d(AppConstants.TAG, it.toString())
        })

        vm.fetchData()
    }
}