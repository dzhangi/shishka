package com.octocavern.data.local

import android.content.Context
import android.util.Log

class ShishkaPrefs(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        Log.i(PREFS_TAG, "saveToken: $token")
        prefs.edit().putString(AUTH_TOKEN_KEY, token).apply()
    }

    fun getToken(): String? {
        Log.i(PREFS_TAG, "getToken: ")
        return prefs.getString(AUTH_TOKEN_KEY, null)
    }

    fun saveRefreshToken(token: String) {
        Log.i(PREFS_TAG, "saveRefreshToken: $token")
        prefs.edit().putString(REFRESH_TOKEN_KEY, token).apply()
    }

    fun getRefreshToken(): String? {
        Log.i(PREFS_TAG, "getRefreshToken: ")
        return prefs.getString(REFRESH_TOKEN_KEY, null)
    }

    companion object {
        private const val PREFS_TAG = "shishka_prefs"
        private const val AUTH_TOKEN_KEY = "auth_token"
        private const val REFRESH_TOKEN_KEY = "refresh_token"
    }
}