package com.example.cleanandroidarchitecture

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(ApplicationComponentManager::class)
//object AppModule {
//
//    @Singleton
//    @Provides
//    fun provideApplication(): Application = MyApplication()
//}