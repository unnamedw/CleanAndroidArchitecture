package com.example.rxjavatest.ui.viewmodel

import android.app.Application
import android.os.Looper
import com.example.rxjavatest.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.logging.Handler
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(application: Application): BaseAndroidViewModel(application) {

    private val _moveToMainEvent = SingleLiveEvent<Any>()
    val moveToMainEvent get() = _moveToMainEvent

    private val handler = android.os.Handler(Looper.getMainLooper())

    fun init() {
        handler.postDelayed({
            _moveToMainEvent.call()
        }, 1000L)
    }

}