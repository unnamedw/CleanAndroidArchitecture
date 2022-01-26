package com.example.cleanandroidarchitecture.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
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

    private lateinit var connectivityManager: ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )

        connectivityManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getSystemService(ConnectivityManager::class.java)
        } else {
            getSystemService(Context.CONNECTIVITY_SERVICE)
        } as ConnectivityManager

        binding.lifecycleOwner = this
        binding.vm = this.vm
        vm.post.observe(this, {
            Log.d(AppConstants.TAG_APPLICATION, it.toString())
        })

        vm.fetchData()

//        initializeWifiConnectionListener()

        Log.d(AppConstants.TAG_NETWORK_TEST, "Is internet available? ${isInternetAvailable(applicationContext)}")

    }

    fun initializeWifiConnectionListener() {
        val wifiNetworkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()

        connectivityManager.registerNetworkCallback(wifiNetworkRequest, object: ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.d(AppConstants.TAG_NETWORK_TEST, "wifi available")
                super.onAvailable(network)
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                Log.d(AppConstants.TAG_NETWORK_TEST, "wifi losing")
                super.onLosing(network, maxMsToLive)
            }

            override fun onLost(network: Network) {
                Log.d(AppConstants.TAG_NETWORK_TEST, "wifi lost")
                super.onLost(network)
            }

            override fun onUnavailable() {
                Log.d(AppConstants.TAG_NETWORK_TEST, "wifi unavailable")
                super.onUnavailable()
            }
        })
    }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

}