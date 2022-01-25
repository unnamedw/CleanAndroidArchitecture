package com.example.cleanandroidarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.cleanandroidarchitecture.AppConstants
import com.example.cleanandroidarchitecture.R
import com.example.cleanandroidarchitecture.databinding.ActivityMainBinding
import com.example.cleanandroidarchitecture.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
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