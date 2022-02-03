package com.example.cleanandroidarchitecture.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.example.cleanandroidarchitecture.AppConstants

class NetworkConnectionManager(context: Context) {

    private val connectivityManager: ConnectivityManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.getSystemService(ConnectivityManager::class.java)
    } else {
        context.getSystemService(Context.CONNECTIVITY_SERVICE)
    } as ConnectivityManager

    fun isInternetAvailable(): Boolean {
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

    private fun initializeWifiConnectionListener() {
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
}