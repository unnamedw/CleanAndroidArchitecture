package com.example.cleanandroidarchitecture

import android.content.SharedPreferences
import com.example.cleanandroidarchitecture.util.PreferencesHelper
import com.example.cleanandroidarchitecture.util.PreferencesHelper.getAppInitFlag
import com.example.cleanandroidarchitecture.util.PreferencesHelper.setAppInitFlag
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowContextImpl
import org.robolectric.shadows.ShadowSharedPreferences
import org.robolectric.shadows.ShadowSystem

@RunWith(RobolectricTestRunner::class)
class RobolectricTest {

    lateinit var prefs: SharedPreferences

    @Before
    fun setUp() {
        val app = RuntimeEnvironment.getApplication() as MyApplication
        prefs = PreferencesHelper.getPreferences(app).also {
            it.setAppInitFlag(value = true)
        }
    }

    @Test
    fun test() {
        prefs.getAppInitFlag().let {
            Assert.assertEquals(it, true)
        }
    }

}