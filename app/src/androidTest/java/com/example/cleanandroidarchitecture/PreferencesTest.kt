package com.example.cleanandroidarchitecture

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cleanandroidarchitecture.util.PreferencesHelper
import com.example.cleanandroidarchitecture.util.PreferencesHelper.getAppInitFlag
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment

@RunWith(AndroidJUnit4::class)
class PreferencesTest {

    @Test
    fun setUp() {
        val app = ApplicationProvider.getApplicationContext<MyApplication>()
        PreferencesHelper.getPreferences(app).getAppInitFlag().also {
            Assert.assertEquals(it, true)
        }
    }
}