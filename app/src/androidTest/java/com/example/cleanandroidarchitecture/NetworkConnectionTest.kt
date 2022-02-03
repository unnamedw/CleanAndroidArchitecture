package com.example.cleanandroidarchitecture

import android.app.Instrumentation
import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cleanandroidarchitecture.util.NetworkConnectionManager
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NetworkConnectionTest {

    lateinit var context: Context
    lateinit var networkManager: NetworkConnectionManager

    @Before
    fun init() {
        context = InstrumentationRegistry.getInstrumentation().context
        networkManager = NetworkConnectionManager(context)
    }

    @Test
    fun 네트워크_연결_테스트() {
        Assert.assertTrue(networkManager.isInternetAvailable())
    }

}