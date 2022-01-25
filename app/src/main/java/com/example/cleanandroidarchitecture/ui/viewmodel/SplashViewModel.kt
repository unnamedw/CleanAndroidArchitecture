package com.example.cleanandroidarchitecture.ui.viewmodel

import android.app.Application
import android.os.Looper
import com.example.cleanandroidarchitecture.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
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