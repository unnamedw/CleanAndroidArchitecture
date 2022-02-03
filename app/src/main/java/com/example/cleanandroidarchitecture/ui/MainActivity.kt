package com.example.cleanandroidarchitecture.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.cleanandroidarchitecture.AppConstants
import com.example.cleanandroidarchitecture.R
import com.example.cleanandroidarchitecture.databinding.ActivityMainBinding
import com.example.cleanandroidarchitecture.ui.adapter.PostAdapter
import com.example.cleanandroidarchitecture.ui.viewmodel.MainViewModel
import com.example.cleanandroidarchitecture.util.NetworkConnectionManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val vm: MainViewModel by viewModels()

    private val postAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.listPost.adapter = this.postAdapter
        binding.vm = this.vm
        vm.posts.observe(this, {
            Log.d(AppConstants.TAG_APPLICATION, it.toString())
            postAdapter.submitDataSet(it.toTypedArray())
        })

        vm.fetchData()

        val networkManager = NetworkConnectionManager(applicationContext)
        Log.d(AppConstants.TAG_NETWORK_TEST, "Is internet available? ${networkManager.isInternetAvailable()}")

    }



}