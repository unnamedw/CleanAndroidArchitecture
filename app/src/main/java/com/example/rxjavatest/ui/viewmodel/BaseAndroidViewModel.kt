package com.example.rxjavatest.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseAndroidViewModel constructor(
    application: Application
): AndroidViewModel(application) {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}