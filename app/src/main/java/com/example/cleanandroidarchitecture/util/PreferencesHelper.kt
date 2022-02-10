package com.example.cleanandroidarchitecture.util

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

object PreferencesHelper {

    private const val SHARED_PREFERENCES_NAME = "my_shared_preferences"
    private const val KEY_IS_INITIAL_START = "key_is_initial_start"

    fun getPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    fun SharedPreferences.setAppInitFlag(value: Boolean) =
        edit { it.putBoolean(KEY_IS_INITIAL_START, value) }

    fun SharedPreferences.getAppInitFlag(): Boolean =
        getBoolean(KEY_IS_INITIAL_START, false)

}