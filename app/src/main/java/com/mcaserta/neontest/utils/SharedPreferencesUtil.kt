package com.mcaserta.neontest.utils

import android.app.Activity
import android.content.SharedPreferences

/**
 * Classe para manipulação de Shared Preferences
 */
class SharedPreferencesUtil(activity: Activity) {
    companion object {
        const val SHARED_TOKEN = "shared_token"
    }

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "NeonTestShared"
    private val sharedPref: SharedPreferences = activity.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    fun save(key: String, value: String) {
        sharedPref.edit().putString(key, value).commit()
    }

    fun get(key: String): String? {
        return sharedPref.getString(key, null)
    }
}