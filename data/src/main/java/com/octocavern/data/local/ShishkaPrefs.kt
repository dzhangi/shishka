package com.octocavern.data.local

import android.content.Context
import android.util.Log
import androidx.core.content.edit


class ShishkaPrefs(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        Log.i(PREFS_TAG, "Save token: $token")
        prefs.edit { putString(AUTH_TOKEN_KEY, token) }
    }

    fun getToken(): String? {
        Log.i(PREFS_TAG, "Get token")
        return prefs.getString(AUTH_TOKEN_KEY, null)
    }

    fun saveRefreshToken(token: String) {
        Log.i(PREFS_TAG, "Save refresh token: $token")
        prefs.edit { putString(REFRESH_TOKEN_KEY, token) }
    }

    fun getRefreshToken(): String? {
        Log.i(PREFS_TAG, "Get refresh token")
        return prefs.getString(REFRESH_TOKEN_KEY, null)
    }

    fun clearTokens() {
        Log.i(PREFS_TAG, "Clear tokens")
        prefs.edit {
            remove(AUTH_TOKEN_KEY)
            remove(REFRESH_TOKEN_KEY)
        }
    }

    fun saveUserId(userId: Int) {
        prefs.edit { putInt(USER_ID, userId) }
    }

    fun getUserId(): Int {
        return prefs.getInt(USER_ID, -1)
    }

    companion object {
        private const val PREFS_TAG = "shishka_prefs"
        private const val AUTH_TOKEN_KEY = "auth_token"
        private const val REFRESH_TOKEN_KEY = "refresh_token"
        private const val USER_ID = "user_id"
    }
}