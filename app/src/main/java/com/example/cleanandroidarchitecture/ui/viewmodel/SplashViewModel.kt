package com.example.cleanandroidarchitecture.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Looper
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanandroidarchitecture.data.repository.PostRepository
import com.example.cleanandroidarchitecture.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    application: Application,
    private val repository: PostRepository
): BaseAndroidViewModel(application) {

    private val _moveToMainEvent = SingleLiveEvent<Any>()
    val moveToMainEvent get() = _moveToMainEvent

    private val handler = android.os.Handler(Looper.getMainLooper())

    fun init() {
        handler.postDelayed({
            _moveToMainEvent.call()
        }, 1500L)
    }
}

class SplashViewModelFactory @Inject constructor(
    private val application: Application,
    private val repository: PostRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel(application, repository) as T
    }
}